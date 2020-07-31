/**
 * Project Name : Generator<br>
 * File Name : DtoGenerator.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/04/21<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator;

import com.google.common.flogger.FluentLogger;

import org.apache.commons.lang3.StringUtils;
import org.thinkit.common.catalog.Extension;
import org.thinkit.common.util.file.FluentFile;
import org.thinkit.generator.command.dto.DtoClassResourceFacade;
import org.thinkit.generator.common.vo.dto.DtoResource;

import lombok.NonNull;

/**
 * DTO定義書を解析してDTOクラスを生成する処理を定義したクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public final class DtoGenerator extends AbstractGenerator {

    /**
     * ログ出力オブジェクト
     */
    private static final FluentLogger logger = FluentLogger.forEnclosingClass();

    /**
     * コンストラクタ
     *
     * @param definitionPath 生成するパスを管理するオブジェクト
     */
    public DtoGenerator(@NonNull DefinitionPath definitionPath) {
        super(definitionPath);
    }

    @Override
    protected boolean run() {

        final DtoResource dtoResource = DtoClassResourceFacade.createResource(super.getFilePath());

        if (dtoResource == null) {
            logger.atSevere().log("DTOクラスのリソース作成処理が異常終了しました。");
            return false;
        }

        final String outputPath = super.getOutputPath(dtoResource.getPackageName());

        if (StringUtils.isEmpty(outputPath)) {
            logger.atSevere().log("出力先パスの取得処理が異常終了しました。");
            return false;
        }

        dtoResource.getResources().forEach((key, value) -> {
            FluentFile.writerOf(outputPath).write(key, Extension.java(), value);
        });

        return true;
    }
}
