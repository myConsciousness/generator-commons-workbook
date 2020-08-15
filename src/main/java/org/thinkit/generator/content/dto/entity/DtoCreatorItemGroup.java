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

package org.thinkit.generator.content.dto.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import org.thinkit.common.util.iterator.FluentIterator;
import org.thinkit.common.util.iterator.IterableNode;
import org.thinkit.framework.content.entity.ContentEntity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * コンテンツ「DTO作成者項目」のオブジェクトグループを管理するデータクラスです。
 * <p>
 * このクラスはFluentインターフェースの概念を応用し設計されています。<br>
 * そのため、以下のようなメソッドチェーンでの操作が可能です。
 *
 * <pre>
 * <code>
 * DtoCreatorItemGroup dtoCreatorItemGroup = DtoCreatorItemGroup.of()
 *                                  .add(dtoCreatorItem1)
 *                                  .add(dtoCreatorItem2);
 * </code>
 * </pre>
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode
public final class DtoCreatorItemGroup
        implements ContentEntity, Iterable<DtoCreatorItem>, IterableNode<DtoCreatorItem>, Serializable {

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = 5501189774410411268L;

    /**
     * DTO作成者項目グループ
     */
    @Getter
    private List<DtoCreatorItem> dtoCreatorItemGroup;

    /**
     * DTO作成者項目グループのサイズ
     */
    private int size;

    /**
     * デフォルトコンストラクタ
     */
    private DtoCreatorItemGroup() {
        this.dtoCreatorItemGroup = new ArrayList<>(0);
        this.size = 0;
    }

    /**
     * コピーコンストラクタ
     *
     * @param dtoCreatorItemGroup DTO作成者項目グループ
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private DtoCreatorItemGroup(@NonNull DtoCreatorItemGroup dtoCreatorItemGroup) {
        this.dtoCreatorItemGroup = new ArrayList<>(dtoCreatorItemGroup.getDtoCreatorItemGroup());
    }

    /**
     * {@link DtoCreatorItemGroup} クラスの新しいインスタンスを生成し返却します。
     *
     * @return {@link DtoCreatorItemGroup} クラスの新しいインスタンス
     */
    public static DtoCreatorItemGroup of() {
        return new DtoCreatorItemGroup();
    }

    /**
     * 引数として渡された {@code dtoCreatorItemGroup} オブジェクトの情報を基に
     * {@link DtoCreatorItemGroup} クラスの新しいインスタンスを生成し返却します。
     *
     * @param dtoCreatorItemGroup DTO作成者項目グループ
     * @return {@link DtoCreatorItemGroup} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static DtoCreatorItemGroup of(@NonNull DtoCreatorItemGroup dtoCreatorItemGroup) {
        return new DtoCreatorItemGroup(dtoCreatorItemGroup);
    }

    /**
     * 引数として渡された {@code dtoCreatorItem} を条件リストへ追加します。
     * <p>
     * この {@link DtoCreatorItemGroup#add(DtoCreatorItem)}
     * メソッドは自分自身のインスタンスを返却するため以下のようなメソッドチェーンでの操作が可能です。
     *
     * <pre>
     * <code>
     * DtoCreatorItemGroup dtoCreatorItemGroup = DtoCreatorItemGroup.of()
     *                                  .add(dtoCreatorItem1)
     *                                  .add(dtoCreatorItem2);
     * </code>
     * </pre>
     *
     * @param dtoCreatorItem DTO作成者項目
     * @return 自分自身のインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public DtoCreatorItemGroup add(@NonNull DtoCreatorItem dtoCreatorItem) {
        this.dtoCreatorItemGroup.add(dtoCreatorItem);
        size++;

        return this;
    }

    /**
     * オブジェクトに含まれる情報が空か判定します。
     *
     * @return オブジェクトに含まれる情報がからの場合は {@code true} 、それ以外は {@code false}
     */
    public boolean isEmpty() {
        return this.size <= 0;
    }

    /**
     * {@link DtoCreatorItem} クラスを総称型として持つストリームを返却します。
     *
     * @return {@link DtoCreatorItem} クラスを総称型として持つストリーム
     */
    public Stream<DtoCreatorItem> stream() {
        return this.dtoCreatorItemGroup.stream();
    }

    @Override
    public List<DtoCreatorItem> nodes() {
        return this.dtoCreatorItemGroup;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Iterator<DtoCreatorItem> iterator() {
        return FluentIterator.of(this);
    }
}