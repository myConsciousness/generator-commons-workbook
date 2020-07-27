package org.thinkit.generator.command.dtogenerator;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import com.google.common.flogger.FluentLogger;

import org.apache.commons.lang3.StringUtils;
import org.thinkit.common.catalog.Catalog;
import org.thinkit.common.command.Command;
import org.thinkit.common.rule.Attribute;
import org.thinkit.common.rule.RuleInvoker;
import org.thinkit.common.util.workbook.FluentSheet;
import org.thinkit.common.util.workbook.FluentWorkbook;
import org.thinkit.common.util.workbook.Matrix;
import org.thinkit.generator.command.Sheet;
import org.thinkit.generator.common.catalog.dtogenerator.DtoItem;
import org.thinkit.generator.common.dto.dtogenerator.ClassCreatorDefinition;
import org.thinkit.generator.rule.dtogenerator.ClassCreatorDefinitionLoader;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

@ToString
@EqualsAndHashCode
final class ClassCreatorDefinitionCollector implements Command<ClassCreatorDefinition> {

    /**
     * ログ出力オブジェクト
     */
    private static final FluentLogger logger = FluentLogger.forEnclosingClass();

    /**
     * 操作対象のシートオブジェクト
     */
    private FluentSheet sheet;

    /**
     * ファイルパス
     */
    private String filePath;

    /**
     * シート名定数
     */
    private enum SheetName implements Sheet {
        定義書;

        @Override
        public String getString() {
            return this.name();
        }
    }

    /**
     * コンテンツ要素定数
     */
    private enum ContentAttribute implements Attribute {
        セル項目コード, セル項目名;

        @Override
        public String getString() {
            return this.name();
        }
    }

    /**
     * コンストラクタ
     *
     * @param filePath DTO定義書のファイルパス
     * @exception IllegalArgumentException ファイルパスがnullまたは空文字列の場合
     */
    public ClassCreatorDefinitionCollector(String filePath) {

        if (StringUtils.isEmpty(filePath)) {
            throw new IllegalArgumentException("wrong parameter was given. File path is required.");
        }

        this.filePath = filePath;
    }

    /**
     * コンストラクタ
     *
     * @param sheet DTO定義書の情報を持つSheetオブジェクト
     */
    public ClassCreatorDefinitionCollector(@NonNull FluentSheet sheet) {
        this.sheet = sheet;
    }

    @Override
    public ClassCreatorDefinition run() {

        if (this.sheet == null) {
            final FluentWorkbook workbook = new FluentWorkbook.Builder().fromFile(this.filePath).build();
            this.sheet = workbook.sheet(SheetName.定義書.name());
        }

        final EnumMap<DtoItem, String> creatorDefinitions = this.getCreatorDefinitions(this.sheet);
        final ClassCreatorDefinition classCreatorDefinition = new ClassCreatorDefinition(
                creatorDefinitions.get(DtoItem.CREATOR), creatorDefinitions.get(DtoItem.CREATION_TIME),
                creatorDefinitions.get(DtoItem.UPDTATE_TIME));

        logger.atInfo().log("クラス作成者情報 = (%s)", classCreatorDefinition);
        return classCreatorDefinition;
    }

    /**
     * セル内に定義された作成者情報を取得し返却します。
     *
     * @param sheet Sheetオブジェクト
     * @return セルに定義された作成者情報
     */
    private EnumMap<DtoItem, String> getCreatorDefinitions(FluentSheet sheet) {

        final List<Map<String, String>> contents = RuleInvoker.of(new ClassCreatorDefinitionLoader()).invoke();
        final EnumMap<DtoItem, String> creatorDefinitions = new EnumMap<>(DtoItem.class);

        for (Map<String, String> elements : contents) {
            final String cellItemName = elements.get(ContentAttribute.セル項目名.name());
            final Matrix baseIndexes = sheet.findCellIndex(cellItemName);

            final String sequence = sheet.getRegionSequence(baseIndexes.getColumn(), baseIndexes.getRow());

            final int itemCode = Integer.parseInt(elements.get(ContentAttribute.セル項目コード.name()));
            creatorDefinitions.put(Catalog.getEnum(DtoItem.class, itemCode), sequence);
        }

        logger.atInfo().log("コンテンツ情報 = (%s)", creatorDefinitions);
        return creatorDefinitions;
    }
}