/**
 * Project Name : Generator<br>
 * File Name : DtoResourceFacade.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/06/13<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.content.dto.rule;

import org.thinkit.framework.content.rule.RuleInvoker;
import org.thinkit.generator.common.command.dto.DtoResourceFormatter;
import org.thinkit.generator.common.vo.dto.DtoMatrix;
import org.thinkit.generator.common.vo.dto.DtoResourceGroup;

import lombok.NonNull;

/**
 * DTOクラスのリソースを生成する処理を集約したファサードクラスです。 DTOクラスのリソースを生成する際には
 * {@link #createResource(String)} を呼び出してください。
 * <p>
 * {@link #createResource(String)}
 * を呼び出す際には第1引数としてDTOクラスの定義情報が記載されたワークブックへのファイルパスを指定してください。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 *
 * @see #createResource(String)
 */
public final class DtoResourceFacade {

    /**
     * デフォルトコンストラクタ
     */
    private DtoResourceFacade() {
    }

    /**
     * 引数として指定された {@code filePath} の値に紐づくワークブックに定義された情報からDTO定義グループを取得し返却します。
     *
     * @param filePath DTO定義書へのファイルパス
     * @return DTO定義書から取得したDTO定義グループ
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static DtoResourceGroup createResource(@NonNull String filePath) {
        final DtoMatrix dtoMatrix = RuleInvoker.of(DtoMatrixCollector.from(filePath)).invoke();
        return DtoResourceFormatter.of(dtoMatrix).execute();
    }
}