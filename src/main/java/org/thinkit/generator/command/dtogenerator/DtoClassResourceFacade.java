/**
 * Project Name : Generator<br>
 * File Name : DtoClassResourceFacade.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/06/13<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.rule.dtogenerator;

import org.thinkit.common.command.CommandInvoker;
import org.thinkit.generator.command.dtogenerator.ClassDefinitionMatrixCollector;
import org.thinkit.generator.common.command.dtogenerator.ClassResourceFormatter;
import org.thinkit.generator.common.dto.dtogenerator.ClassDefinitionMatrix;
import org.thinkit.generator.common.dto.dtogenerator.ClassResource;

import lombok.NonNull;

/**
 * DTOクラスのリソースを生成する処理を集約したファサードクラスです。<br>
 * DTOクラスのリソースを生成する際には {@link #createResource(String)} を呼び出してください。<br>
 * {@link #createResource(String)} を呼び出す際には、<br>
 * 第1引数としてDTOクラスの定義情報が記載されたExcelファイルのパスを指定してください。<br>
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 *
 * @see #createResource(String)
 */
public final class DtoClassResourceFacade {

    /**
     * デフォルトコンストラクタ
     */
    private DtoClassResourceFacade() {
    }

    /**
     * 指定されたファイルパスに定義された情報を基にDTOクラスのリソースを生成します。<br>
     * 引数に {@code null} が指定された場合は実行時に必ず失敗します。<br>
     * <br>
     * {@link ClassResourceFormatter#getClassResource()} で取得する連想配列は<br>
     * 以下の情報を格納しています。<br>
     * 1, Key ・・・ クラス名<br>
     * 2, Value ・・・ クラス名に紐づくDTOクラスのリソース<br>
     *
     * @param filePath 定義書のファイルパス
     * @return 生成されたDTOクラスのリソース情報
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static ClassResource createResource(@NonNull String filePath) {

        final ClassDefinitionMatrix classDefintionMatrix = CommandInvoker
                .of(new ClassDefinitionMatrixCollector(filePath)).invoke();

        return CommandInvoker.of(new ClassResourceFormatter(classDefintionMatrix)).invoke();
    }
}