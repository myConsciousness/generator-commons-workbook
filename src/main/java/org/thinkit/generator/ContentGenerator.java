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

package org.thinkit.generator;

import org.thinkit.common.catalog.Extension;
import org.thinkit.common.util.file.FluentFile;
import org.thinkit.generator.content.dto.rule.DtoResourceFacade;

import lombok.NonNull;

/**
 * コンテンツ定義書を解析してコンテンツファイルを生成する処理を定義したクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public final class ContentGenerator extends AbstractGenerator {

    /**
     * コンストラクタ
     *
     * @param definitionPath 生成するパスを管理するオブジェクト
     */
    public ContentGenerator(@NonNull DefinitionPath definitionPath) {
        super(definitionPath);
    }

    @Override
    protected boolean run() {

        DtoResourceFacade.createResource(super.getFilePath()).forEach(dtoResource -> {
            FluentFile.writerOf(super.getOutputPath(dtoResource.getPackageName())).write(dtoResource.getResourceName(),
                    Extension.json(), dtoResource.getResource());
        });

        return true;
    }
}
