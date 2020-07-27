package org.thinkit.generator.command.dtogenerator;

import org.thinkit.common.command.Command;
import org.thinkit.common.util.workbook.FluentSheet;
import org.thinkit.generator.common.dto.dtogenerator.ClassCreatorDefinition;

import lombok.NonNull;

public final class ClassCreatorDefinitionCollector implements Command<ClassCreatorDefinition> {

    /**
     * 操作対象のシートオブジェクト
     */
    private FluentSheet sheet;

    /**
     * コンストラクタ
     *
     * @param sheet DTO定義書の情報を持つSheetオブジェクト
     */
    public ClassCreatorDefinitionCollector(@NonNull FluentSheet sheet) {
        this.sheet = sheet;
    }

    @Override
    public ClassCreatorDefinition run() {
        return null;
    }
}