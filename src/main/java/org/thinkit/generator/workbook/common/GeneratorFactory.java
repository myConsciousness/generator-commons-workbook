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

package org.thinkit.generator.workbook.common;

import org.thinkit.generator.common.Generator;
import org.thinkit.generator.common.catalog.GeneratorDivision;

import lombok.NonNull;

/**
 * 各業務に応じた生成器を生成する抽象生成器ファクトリクラスの実装クラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
final class GeneratorFactory extends AbstractGeneratorFactory {

    /**
     * {@link GeneratorFactory} のシングルトンインスタンスを保持するインナークラスです。<br>
     * {@link GeneratorFactory} シングルトンインスタンスは初回参照時にメモリに読み込まれます。
     */
    private static class InstanceHolder {

        /**
         * シングルトンインスタンス
         */
        private static final GeneratorFactory ISNTANCE = new GeneratorFactory();
    }

    /**
     * デフォルトコンストラクタ
     */
    private GeneratorFactory() {
    }

    /**
     * 生成器ファクトリクラスのシングルトンインスタンスを返却します。
     *
     * @return 生成器ファクトリクラスのシングルトンインスタンス
     */
    protected static AbstractGeneratorFactory getInstance() {
        return InstanceHolder.ISNTANCE;
    }

    @Override
    protected Generator createGenerator(@NonNull GeneratorDivision generatorDivision,
            @NonNull DefinitionPath definitionPath) {

                // TODO: このロジックを引き続き使用するかは要検討
        // return switch (generatorDivision) {
        //     case DTO_DEFINITION -> new DtoGenerator(definitionPath);
        //     case CONTENT_DEFINITION -> new ContentGenerator(definitionPath);
        //     default -> null;
        // };

        return null;
    }
}