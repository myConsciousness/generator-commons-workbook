/*
 * Copyright 2020 Kato Shinya.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package org.thinkit.generator.content.dto.rule;

import java.util.EnumMap;
import java.util.Map;

import com.google.common.flogger.FluentLogger;

import org.thinkit.common.catalog.Catalog;
import org.thinkit.common.util.workbook.FluentSheet;
import org.thinkit.common.util.workbook.Matrix;
import org.thinkit.framework.content.ContentInvoker;
import org.thinkit.framework.content.rule.Rule;
import org.thinkit.generator.common.catalog.dto.DtoItem;
import org.thinkit.generator.common.vo.dto.DtoMeta;
import org.thinkit.generator.content.dto.DtoMetaItemLoader;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * Excelに記載されたDTO定義の名前部分の情報を読み取る処理を定義したルールクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode
final class DtoMetaCollector implements Rule<DtoMeta> {

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
    private DtoMetaCollector() {
    }

    /**
     * コンストラクタ
     *
     * @param sheet 操作する対象のシートオブジェクト
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private DtoMetaCollector(@NonNull FluentSheet sheet) {
        this.sheet = sheet;
    }

    /**
     * 引数として渡された {@code sheet} オブジェクトを基に {@link DtoMetaCollector}
     * クラスの新しいインスタンスを生成し返却します。
     *
     * @param sheet 操作する対象のシートオブジェクト
     * @return {@link DtoMetaCollector} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     * @see FluentSheet
     */
    public static Rule<DtoMeta> from(@NonNull FluentSheet sheet) {
        return new DtoMetaCollector(sheet);
    }

    @Override
    public DtoMeta execute() {

        final Map<DtoItem, String> dtoMeta = this.getDtoMeta(this.sheet);

        return DtoMeta.of(dtoMeta.get(DtoItem.VERSION), dtoMeta.get(DtoItem.PROJECT_NAME),
                dtoMeta.get(DtoItem.PACKAGE_NAME), dtoMeta.get(DtoItem.PHYSICAL_NAME),
                dtoMeta.get(DtoItem.LOGICAL_NAME), dtoMeta.get(DtoItem.DESCRIPTION));
    }

    /**
     * セル内に定義された作成者情報を取得し返却します。
     *
     * @param sheet Sheetオブジェクト
     * @return セルに定義されたDTOメタ情報
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private Map<DtoItem, String> getDtoMeta(@NonNull FluentSheet sheet) {

        final Map<DtoItem, String> dtoMeta = new EnumMap<>(DtoItem.class);

        ContentInvoker.of(DtoMetaItemLoader.of()).invoke().forEach(dtoMetaItem -> {
            final Matrix baseIndexes = sheet.findCellIndex(dtoMetaItem.getCellItemName());
            final String sequence = sheet.getRegionSequence(baseIndexes.getColumn(), baseIndexes.getRow());
            dtoMeta.put(Catalog.getEnum(DtoItem.class, dtoMetaItem.getCellItemCode()), sequence);
        });

        logger.atFinest().log("DTOメタ = (%s)", dtoMeta);
        return dtoMeta;
    }
}