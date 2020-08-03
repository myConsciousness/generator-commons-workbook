/**
 * Project Name : Generator<br>
 * File Name : ContentGenerator.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/08/02<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator;

import org.thinkit.common.catalog.Extension;
import org.thinkit.common.util.file.FluentFile;
import org.thinkit.generator.command.dto.DtoResourceFacade;

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
