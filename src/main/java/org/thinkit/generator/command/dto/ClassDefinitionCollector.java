/**
 * Project Name : Generator<br>
 * File Name : dtoDefinitionCollector.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/06/13<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.command.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.common.flogger.FluentLogger;

import org.apache.commons.lang3.StringUtils;
import org.thinkit.common.command.Command;
import org.thinkit.common.rule.Attribute;
import org.thinkit.common.rule.RuleInvoker;
import org.thinkit.common.util.workbook.FluentSheet;
import org.thinkit.common.util.workbook.FluentWorkbook;
import org.thinkit.common.util.workbook.Matrix;
import org.thinkit.generator.command.Sheet;
import org.thinkit.generator.common.catalog.dtogenerator.DtoItem;
import org.thinkit.generator.common.vo.dto.DtoDefinition;
import org.thinkit.generator.common.vo.dto.DtoField;
import org.thinkit.generator.rule.dtogenerator.ClassDefinitionCellLoader;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * Excelに記載されたDTO定義のクラス本体部分の情報を読み取る処理を定義したコマンドクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode
final class ClassDefinitionCollector implements Command<List<DtoDefinition>> {

    /**
     * ログ出力オブジェクト
     */
    private static final FluentLogger logger = FluentLogger.forEnclosingClass();

    /**
     * 再帰処理開始時のインデックス
     */
    private static final int RECURSIVE_START_INDEX = 0;

    /**
     * 再帰処理開始時の基準層
     */
    private static final int RECURSIVE_BASE_LAYER = 1;

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
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     * @throws IllegalArgumentException ファイルパスがnullまたは空文字列の場合
     */
    public ClassDefinitionCollector(@NonNull String filePath) {

        if (StringUtils.isBlank(filePath)) {
            throw new IllegalArgumentException("wrong parameter was given. File path is required.");
        }

        this.filePath = filePath;
    }

    /**
     * コンストラクタ
     *
     * @param sheet DTO定義書の情報を持つSheetオブジェクト
     */
    public ClassDefinitionCollector(@NonNull FluentSheet sheet) {
        this.sheet = sheet;
    }

    @Override
    public List<DtoDefinition> run() {
        if (this.sheet == null) {
            final FluentWorkbook workbook = new FluentWorkbook.Builder().fromFile(this.filePath).build();
            this.sheet = workbook.sheet(SheetName.定義書.name());
        }

        final List<DtoDefinition> dtoDefinitionList = this.getDtoDefinitionList(sheet);

        if (dtoDefinitionList.isEmpty()) {
            logger.atSevere().log("DTO定義情報を取得できませんでした。");
            return null;
        }

        return dtoDefinitionList;
    }

    /**
     * Excelに定義されたマトリクステーブルからDTO定義情報群を取得し返却します。
     *
     * @param sheet Sheetオブジェクト
     * @return DTO定義情報群
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     *
     * @see #getdtoDefinitionRecursively(List, List, List, int, int)
     */
    private List<DtoDefinition> getDtoDefinitionList(@NonNull FluentSheet sheet) {

        final List<Map<String, String>> contents = RuleInvoker.of(ClassDefinitionCellLoader.of()).invoke();

        final String baseCellItem = this.getContentItem(contents, DtoItem.LOGICAL_DELETE);
        final Matrix baseIndexes = sheet.findCellIndex(baseCellItem);

        final List<Map<String, String>> matrixList = sheet.getMatrixList(baseIndexes.getColumn(), baseIndexes.getRow());
        logger.atInfo().log("マトリクスリスト = (%s)", matrixList);

        final List<DtoDefinition> dtoDefinitionList = new ArrayList<>();
        this.craetedtoDefinitionRecursively(RecursiveRequiredParameters.of(matrixList, contents, dtoDefinitionList,
                RECURSIVE_START_INDEX, RECURSIVE_BASE_LAYER));

        logger.atInfo().log("DTO定義情報群 = (%s)", dtoDefinitionList);

        return dtoDefinitionList;
    }

    /**
     * 引数として指定されたマトリクスリストから再帰的にDTO定義情報群を生成します。<br>
     * 再帰処理は各レコードが子クラスを持っている場合に実行されます。
     *
     * @param recursiveRequiredParameters 再帰処理時に必須となる情報を格納したデータクラス
     * @return 子クラスを生成する際に使用したレコード数
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     *
     * @see RecursiveRequiredParameters
     */
    private int craetedtoDefinitionRecursively(@NonNull final RecursiveRequiredParameters recursiveRequiredParameters) {

        final List<Map<String, String>> matrixList = recursiveRequiredParameters.getMatrixList();
        final List<Map<String, String>> contents = recursiveRequiredParameters.getContents();
        final List<DtoDefinition> dtoDefinitionList = recursiveRequiredParameters.getDtoDefinitionList();
        final int startIndex = recursiveRequiredParameters.getStartIndex();
        final int baseItemLayer = recursiveRequiredParameters.getBaseItemLayer();

        logger.atInfo().log("開始インデックス = (%s)", startIndex);
        logger.atInfo().log("基準項目層 = (%s)", baseItemLayer);

        DtoDefinition parentDtoDefinition = new DtoDefinition();
        List<DtoField> dtoFieldList = new ArrayList<>();

        int recordCounter = 0;
        for (int i = startIndex, size = matrixList.size(); i < size; i++) {
            final Map<String, String> record = matrixList.get(i);

            final boolean deleted = this
                    .convertStringToBoolean(record.get(this.getCellItemName(contents, DtoItem.LOGICAL_DELETE)));

            if (deleted) {
                logger.atInfo().log("論理削除されたレコードのためスキップします。");
                logger.atInfo().log("スキップされたレコード = (%s)", record);
                recordCounter++;
                continue;
            }

            final int layer = Integer.parseInt(record.get(this.getCellItemName(contents, DtoItem.LAYER)));
            logger.atInfo().log("レコードから取得した項目層 = (%s)", layer);

            if (layer + 1 < baseItemLayer) {
                logger.atInfo().log("%s層の処理を終了します。", baseItemLayer);
                logger.atInfo().log("戻り先の層 = (%s)", baseItemLayer - 2);
                break;
            }

            if (layer == baseItemLayer - 1 && layer % 2 == 0) {
                parentDtoDefinition = new DtoDefinition();
                dtoFieldList = new ArrayList<>();

                parentDtoDefinition.setDtoFieldList(dtoFieldList);
                dtoDefinitionList.add(parentDtoDefinition);

                this.createDtoDefinition(contents, record, parentDtoDefinition);
            } else {
                if (layer > baseItemLayer) {
                    logger.atInfo().log("子クラス情報を生成するため再帰処理を開始します。");

                    List<DtoDefinition> childDtoDefinitionList = new ArrayList<>();
                    final int skipCounter = this.craetedtoDefinitionRecursively(RecursiveRequiredParameters
                            .of(matrixList, contents, childDtoDefinitionList, i, baseItemLayer + 2));

                    dtoFieldList.get(dtoFieldList.size() - 1).setChildDtoDefinitionList(childDtoDefinitionList);

                    logger.atInfo().log("レコード番号 = (%s)", i);
                    logger.atInfo().log("スキップ数 = (%s)", skipCounter);
                    i += skipCounter - 1;
                } else {
                    this.createDtoField(contents, record, dtoFieldList);
                }
            }

            recordCounter++;
        }

        logger.atInfo().log("DTO定義情報（途中経過） = (%s)", dtoDefinitionList);
        return recordCounter;
    }

    /**
     * マトリクスから取得したレコードを基にDTO定義情報を生成します。
     *
     * @param content       コンテンツ
     * @param record        マトリクスレコード
     * @param dtoDefinition DTO定義情報
     */
    private void createDtoDefinition(final List<Map<String, String>> content, final Map<String, String> record,
            final DtoDefinition dtoDefinition) {

        final String className = record.get(this.getCellItemName(content, DtoItem.VARIABLE_NAME));
        final String description = record.get(this.getCellItemName(content, DtoItem.DESCRIPTION));

        dtoDefinition.setClassName(className);
        dtoDefinition.setDescription(description);

        logger.atInfo().log("DTO定義情報 = (%s)", dtoDefinition.toString());
    }

    /**
     * マトリクスから取得した情報を基にクラス項目情報を生成します。
     *
     * @param content      コンテンツ
     * @param record       マトリクスレコード
     * @param dtoFieldList DTOフィールドリスト
     */
    private void createDtoField(List<Map<String, String>> content, Map<String, String> record,
            List<DtoField> dtoFieldList) {

        final String variableName = record.get(this.getCellItemName(content, DtoItem.VARIABLE_NAME));
        final String dataType = record.get(this.getCellItemName(content, DtoItem.DATA_TYPE));
        final String initialValue = record.get(this.getCellItemName(content, DtoItem.INITIAL_VALUE));
        final boolean invariant = this
                .convertStringToBoolean(record.get(this.getCellItemName(content, DtoItem.INVARIANT)));
        final String description = record.get(this.getCellItemName(content, DtoItem.DESCRIPTION));

        final DtoField classItemDefinition = new DtoField(variableName, dataType, initialValue, invariant, description);

        dtoFieldList.add(classItemDefinition);

        logger.atInfo().log("DTOフィールド = (%s)", classItemDefinition);
    }

    /**
     * 文字列を真偽値に変換します。
     * <p>
     * 真偽値へ変換する際のルールは下記の通りです。<br>
     * 当該メソッドでは文字列に対するトリム加工は行いません。
     * <p>
     * 1, 文字列がnullの場合: {@code false}<br>
     * 2, 文字列が空文字列の場合: {@code false}<br>
     * 3, 上記以外の場合: {@code true}<br>
     *
     * @param sequence 変換対象の文字列
     * @return 文字列がnullまたは空文字列の場合は {@code false} 、それ以外は {@code true}
     */
    private boolean convertStringToBoolean(final String sequence) {
        return !StringUtils.isEmpty(sequence);
    }

    /**
     * コンテンツリストから引数として指定されたセル項目オブジェクトのコード値と紐づくセル項目名を取得し返却します。
     *
     * @param content コンテンツリスト
     * @param dtoItem 取得対象のセル項目コードが定義されたオブジェクト
     * @return 引数として指定されたセル項目コードに紐づくセル項目名
     *
     * @exception IllegalArgumentException コンテンツリストが空の場合、またはセル項目オブジェクトが {@code null}
     *                                     の場合
     */
    private String getCellItemName(@NonNull List<Map<String, String>> content, @NonNull DtoItem dtoItem) {
        assert !content.isEmpty();

        final int cellItemCode = dtoItem.getCode();
        logger.atInfo().log("引数として渡されたセル項目コード = (%s)", cellItemCode);

        for (Map<String, String> elements : content) {
            final int code = Integer.parseInt(elements.get(ContentAttribute.セル項目コード.name()));
            logger.atInfo().log("コンテンツから取得したセル項目コード = (%s)", code);

            if (cellItemCode == code) {
                return elements.get(ContentAttribute.セル項目名.name());
            }
        }

        logger.atWarning().log("指定されたセル項目コードに紐づく要素がコンテンツに定義されていません。");
        return "";
    }

    /**
     * 指定されたセル項目に紐づくコンテンツ項目を取得し返却します。
     *
     * @param nodeList コンテンツ項目リスト
     * @param dtoItem  取得対象のセル項目
     * @return 取得対象のセル項目に紐づくコンテンツ項目
     */
    private String getContentItem(List<Map<String, String>> nodeList, DtoItem dtoItem) {

        for (Map<String, String> node : nodeList) {
            final int cellItemCode = Integer.parseInt(node.get(ContentAttribute.セル項目コード.getString()));

            if (dtoItem.getCode() == cellItemCode) {
                final String contentItem = node.get(ContentAttribute.セル項目名.getString());
                logger.atInfo().log("取得したコンテンツ項目 = (%s)", contentItem);
                return contentItem;
            }
        }

        logger.atInfo().log("指定されたコンテンツ項目を取得できませんでした。");
        return "";
    }

    /**
     * DTO定義情報を取得する際の再帰処理で必要となるパラメータ情報を管理するデータクラスです。
     *
     * @author Kato Shinya
     * @since 1.0
     * @version 1.0
     */
    @Getter
    @ToString
    @EqualsAndHashCode(callSuper = false)
    @RequiredArgsConstructor(staticName = "of")
    private static class RecursiveRequiredParameters implements Serializable {

        /**
         * シリアルバージョンUID
         */
        private static final long serialVersionUID = 3989477460378492335L;

        /**
         * Excelの定義書シートから取得したマトリクスの生データリスト
         */
        @NonNull
        private final List<Map<String, String>> matrixList;

        /**
         * クラス項目のコンテンツ情報が格納されたマップ
         */
        @NonNull
        private final List<Map<String, String>> contents;

        /**
         * DTO定義情報リスト
         */
        @NonNull
        private final List<DtoDefinition> dtoDefinitionList;

        /**
         * 探索開始インデックス
         */
        private final int startIndex;

        /**
         * 基準項目層
         */
        private final int baseItemLayer;
    }
}