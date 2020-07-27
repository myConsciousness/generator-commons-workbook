/**
 * Project Name :Generator<br>
 * File Name : ClassDefinitionMatrixReader.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/04/23<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.command.dtogenerator;

import com.google.common.flogger.FluentLogger;

import org.apache.commons.lang3.StringUtils;
import org.thinkit.common.command.Command;
import org.thinkit.common.command.CommandInvoker;
import org.thinkit.common.util.workbook.FluentSheet;
import org.thinkit.common.util.workbook.FluentWorkbook;
import org.thinkit.generator.command.Sheet;
import org.thinkit.generator.common.dto.dtogenerator.ClassDefinitionMatrix;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Excelに記述された定義書シートからクラス定義マトリクス情報を抽出する処理を行うルールです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode(callSuper = false)
final class ClassDefinitionMatrixCollector implements Command<ClassDefinitionMatrix> {

    /**
     * ログ出力オブジェクト
     */
    private static final FluentLogger logger = FluentLogger.forEnclosingClass();

    /**
     * ファイルパス
     */
    @Getter(AccessLevel.PRIVATE)
    private String filePath = "";

    /**
     * デフォルトコンストラクタ
     */
    @SuppressWarnings("unused")
    private ClassDefinitionMatrixCollector() {
    }

    /**
     * コンストラクタ
     *
     * @param filePath DTO定義書のファイルパス
     * @exception IllegalArgumentException ファイルパスがnullまたは空文字列の場合
     */
    public ClassDefinitionMatrixCollector(String filePath) {
        logger.atInfo().log("ファイルパス = (%s)", filePath);

        if (StringUtils.isEmpty(filePath)) {
            throw new IllegalArgumentException("wrong parameter was given. File path is required.");
        }

        this.filePath = filePath;
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
    public ClassDefinitionMatrix run() {

        final FluentWorkbook workbook = new FluentWorkbook.Builder().fromFile(this.getFilePath()).build();
        final FluentSheet sheet = workbook.sheet(SheetName.定義書.name());

        final ClassDefinitionMatrix classDefinitionMatrix = new ClassDefinitionMatrix(
                CommandInvoker.of(new ClassNameDefinitionCollector(sheet)).invoke(),
                CommandInvoker.of(new ClassCreatorDefinitionCollector(sheet)).invoke(),
                CommandInvoker.of(new ClassDefinitionCollector(sheet)).invoke());

        logger.atInfo().log("クラス定義情報マトリクス = (%s)", classDefinitionMatrix);
        return null;
    }
}
