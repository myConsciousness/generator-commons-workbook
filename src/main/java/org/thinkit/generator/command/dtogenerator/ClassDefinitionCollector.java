package org.thinkit.generator.command.dtogenerator;

import java.util.List;

import org.thinkit.common.command.Command;
import org.thinkit.common.util.workbook.FluentSheet;
import org.thinkit.generator.common.dto.dtogenerator.ClassDefinition;

import lombok.NonNull;

public final class ClassDefinitionCollector implements Command<List<ClassDefinition>> {

    /**
     * 操作対象のシートオブジェクト
     */
    private FluentSheet sheet;

    /**
     * コンストラクタ
     *
     * @param sheet DTO定義書の情報を持つSheetオブジェクト
     */
    public ClassDefinitionCollector(@NonNull FluentSheet sheet) {
        this.sheet = sheet;
    }

    @Override
    public List<ClassDefinition> run() {
        return null;
    }
}