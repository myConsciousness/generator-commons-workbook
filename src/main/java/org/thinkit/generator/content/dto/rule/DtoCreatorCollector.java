/**
 * Project Name : Generator<br>
 * File Name : DtoCreatorCollector.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/06/13<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.command.dto;

import java.util.EnumMap;
import java.util.Map;

import com.google.common.flogger.FluentLogger;

import org.thinkit.common.catalog.Catalog;
import org.thinkit.common.command.Command;
import org.thinkit.common.rule.RuleInvoker;
import org.thinkit.common.util.workbook.FluentSheet;
import org.thinkit.common.util.workbook.Matrix;
import org.thinkit.generator.common.catalog.dto.DtoItem;
import org.thinkit.generator.common.vo.dto.DtoCreator;
import org.thinkit.generator.rule.dto.DtoCreatorItemLoader;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * Excelに記載されたDTO定義の作成者部分の情報を読み取る処理を定義したコマンドクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode
final class DtoCreatorCollector implements Command<DtoCreator> {

    /**
     * ログ出力オブジェクト
     */
    private static final FluentLogger logger = FluentLogger.forEnclosingClass();

    /**
     * 操作対象のシートオブジェクト
     */
    private FluentSheet sheet;

    /**
     * デフォルトコンストラクタ
     */
    private DtoCreatorCollector() {
    }

    /**
     * コンストラクタ
     *
     * @param sheet 操作する対象のシートオブジェクト
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private DtoCreatorCollector(@NonNull FluentSheet sheet) {
        this.sheet = sheet;
    }

    /**
     * 引数として渡された {@code sheet} を基に {@link DtoCreatorCollector}
     * クラスの新しいインスタンスを生成し返却します。
     *
     * @param sheet 操作する対象のシートオブジェクト
     * @return {@link DtoCreatorCollector} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     * @see FluentSheet
     */
    public static Command<DtoCreator> from(@NonNull FluentSheet sheet) {
        return new DtoCreatorCollector(sheet);
    }

    @Override
    public DtoCreator run() {

        final Map<DtoItem, String> dtoCreator = this.getDtoCreator(this.sheet);

        return DtoCreator.of(dtoCreator.get(DtoItem.CREATOR), dtoCreator.get(DtoItem.CREATION_TIME),
                dtoCreator.get(DtoItem.UPDTATE_TIME));
    }

    /**
     * セル内に定義されたDTO作成者項目を取得し返却します。
     *
     * @param sheet Sheetオブジェクト
     * @return セルに定義されたDTO作成者項目
     */
    private Map<DtoItem, String> getDtoCreator(FluentSheet sheet) {

        final Map<DtoItem, String> dtoCreator = new EnumMap<>(DtoItem.class);

        RuleInvoker.of(DtoCreatorItemLoader.of()).invoke().forEach(dtoCreatorItem -> {
            final Matrix baseIndexes = sheet.findCellIndex(dtoCreatorItem.getCellItemName());
            final String sequence = sheet.getRegionSequence(baseIndexes.getColumn(), baseIndexes.getRow());
            dtoCreator.put(Catalog.getEnum(DtoItem.class, dtoCreatorItem.getCellItemCode()), sequence);
        });

        logger.atFinest().log("DTO作成者 = (%s)", dtoCreator);
        return dtoCreator;
    }
}