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

import org.thinkit.generator.common.Generator;
import org.thinkit.generator.common.catalog.GeneratorDivision;

import lombok.NonNull;

/**
 * 各業務に対応した生成器を生成する抽象ファクトリクラスです。
 * <p>
 * 使用する際には必要な生成器と紐づく生成器区分を引数として、<br>
 * {@link #create(GeneratorDivision, DefinitionPath)} を呼び出してください。<br>
 * 各業務に必要な生成器が返却されます。
 * <p>
 * 当抽象クラスを継承する全ての具象サブクラスは必ず {@link #createGenerator(GeneratorDivision, DefinitionPath)} を実装する必要があります。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
abstract class AbstractGeneratorFactory {

    /**
     * 引数として指定された生成器区分から各業務処理に対応する生成器を取得し返却します。<br>
     * 当ファクトリ処理が正常に終了するためには当抽象クラスを継承したそれぞれの具象ファクトリクラスが<br>
     * {@link #createGenerator(GeneratorDivision, DefinitionPath)} を各業務に対応した生成器を返却するように正しく実装している必要があります。
     * <p>
     * 起動する生成器区分に加えて第2引数として生成器が操作する対象のファイルへのパスを指定する必要があります。<br>
     * 引数として{@code null}が渡された場合は実行時に必ず失敗します。<br>
     * 引数チェックに関しては当メソッドにて実施するため、<br>
     * {@link #createGenerator(GeneratorDivision, DefinitionPath)} の実装時に引数の検査処理を実装する必要はありません。<br>
     * <br>
     * 業務に応じた生成器を取得する際にはサブクラスで実装された {@link #createGenerator(GeneratorDivision, DefinitionPath)} ではなく、<br>
     * {@link #create(GeneratorDivision, DefinitionPath)} を呼び出してください。
     *
     * @param generatorDivision 生成器区分
     * @param definitionPath    生成する定義を管理するオブジェクト
     * @return 生成器
     *
     * @see {@link #createGenerator(GeneratorDivision, String)}
     *
     * @exception NullPointerException 生成器区分が {@code null} の場合
     */
    public final Generator create(@NonNull final GeneratorDivision generatorDivision,
            @NonNull DefinitionPath definitionPath) {
        return this.createGenerator(generatorDivision, definitionPath);
    }

    /**
     * 引数として指定された生成器区分に基づいて、<br>
     * 各業務に対応した生成器を取得し返却する処理を定義する抽象メソッドです。
     * <p>
     * この抽象メソッドは当抽象クラスの {@link #create(GeneratorDivision, DefinitionPath)} で使用されるため、<br>
     * 当抽象クラスを継承した具象ファクトリクラスは必ずこの抽象メソッドを実装する必要があります。
     * <p>
     * この抽象メソッドを実装する際には以下のことを遵守してください。
     * <p>
     * 1, 引数として指定された生成器区分に応じたファクトリクラスを呼び出すこと。<br>
     * 2, 返却する生成器は必ず生成器区分に応じたクラスであること。<br>
     *
     * @param generatorDivision 生成器区分
     * @param definitionPath    生成する定義を管理するオブジェクト
     * @return 生成器
     *
     * @see {@link #create(GeneratorDivision, DefinitionPath)}
     *
     * @exception NullPointerException 生成器区分が {@code null} の場合
     */
    protected abstract Generator createGenerator(@NonNull GeneratorDivision generatorDivision,
            @NonNull DefinitionPath definitionPath);
}