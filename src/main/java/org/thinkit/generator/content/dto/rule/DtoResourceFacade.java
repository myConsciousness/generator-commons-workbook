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