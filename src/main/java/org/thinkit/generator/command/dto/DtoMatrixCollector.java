/**
 * Project Name :Generator<br>
 * File Name : DtoDefinitionMatrixCollector.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/04/23<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.command.dto;

import com.google.common.flogger.FluentLogger;

import org.apache.commons.lang3.StringUtils;
import org.thinkit.common.command.Command;
import org.thinkit.common.command.CommandInvoker;
import org.thinkit.common.util.workbook.FluentSheet;
import org.thinkit.common.util.workbook.FluentWorkbook;
import org.thinkit.generator.command.Sheet;
import org.thinkit.generator.common.vo.dto.DtoDefinitionMatrix;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * Excelに記述された定義書シートからクラス定義情報を読み取る処理を定義したコマンドクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode(callSuper = false)
final class DtoDefinitionMatrixCollector implements Command<DtoDefinitionMatrix> {

    /**
     * ログ出力オブジェクト
     */
    private static final FluentLogger logger = FluentLogger.forEnclosingClass();

    /**
     * ファイルパス
     */
    private String filePath;

    /**
     * デフォルトコンストラクタ
     */
    private DtoDefinitionMatrixCollector() {
    }

    /**
     * コンストラクタ
     *
     * @param filePath DTO定義書のファイルパス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     * @throws IllegalArgumentException ファイルパスが空文字列の場合
     */
    private DtoDefinitionMatrixCollector(@NonNull String filePath) {

        if (StringUtils.isBlank(filePath)) {
            throw new IllegalArgumentException("wrong parameter was given. File path is required.");
        }

        this.filePath = filePath;
    }

    /**
     * 引数として指定された定義書へのファイルパスを基に {@link DtoDefinitionMatrixCollector}
     * クラスの新しいインスタンスを生成し返却します。
     *
     * @param filePath DTO定義書へのパス
     * @return {@link DtoDefinitionMatrixCollector} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     * @throws IllegalArgumentException ファイルパスが空文字列の場合
     */
    public static Command<DtoDefinitionMatrix> from(@NonNull String filePath) {
        return new DtoDefinitionMatrixCollector(filePath);
    }

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

    @Override
    public DtoDefinitionMatrix run() {

        final FluentWorkbook workbook = FluentWorkbook.builder().fromFile(this.filePath).build();
        final FluentSheet sheet = workbook.sheet(SheetName.定義書.name());

        final DtoDefinitionMatrix dtoDefinitionMatrix = new DtoDefinitionMatrix(
                CommandInvoker.of(DtoMetaCollector.from(sheet)).invoke(),
                CommandInvoker.of(DtoCreatorCollector.from(sheet)).invoke(),
                CommandInvoker.of(DtoDefinitionCollector.from(sheet)).invoke());

        logger.atInfo().log("DTO定義マトリクス = (%s)", dtoDefinitionMatrix);
        return dtoDefinitionMatrix;
    }
}
