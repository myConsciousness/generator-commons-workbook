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
 * コンテンツ「DTOメタ項目」のオブジェクトグループを管理するデータクラスです。
 * <p>
 * このクラスはFluentインターフェースの概念を応用し設計されています。<br>
 * そのため、以下のようなメソッドチェーンでの操作が可能です。
 *
 * <pre>
 * <code>
 * DtoMetaItemGroup dtoMetaItemGroup = DtoMetaItemGroup.of()
 *                                  .add(dtoMetaItem1)
 *                                  .add(dtoMetaItem2);
 * </code>
 * </pre>
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode
public final class DtoMetaItemGroup
        implements ContentEntity, Iterable<DtoMetaItem>, IterableNode<DtoMetaItem>, Serializable {

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = 5501189774410411268L;

    /**
     * DTOメタ項目グループ
     */
    @Getter
    private List<DtoMetaItem> dtoMetaItemGroup;

    /**
     * DTOメタ項目グループのサイズ
     */
    private int size;

    /**
     * デフォルトコンストラクタ
     */
    private DtoMetaItemGroup() {
        this.dtoMetaItemGroup = new ArrayList<>(0);
        this.size = 0;
    }

    /**
     * コピーコンストラクタ
     *
     * @param dtoMetaItemGroup DTOメタ項目グループ
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private DtoMetaItemGroup(@NonNull DtoMetaItemGroup dtoMetaItemGroup) {
        this.dtoMetaItemGroup = new ArrayList<>(dtoMetaItemGroup.getDtoMetaItemGroup());
    }

    /**
     * {@link DtoMetaItemGroup} クラスの新しいインスタンスを生成し返却します。
     *
     * @return {@link DtoMetaItemGroup} クラスの新しいインスタンス
     */
    public static DtoMetaItemGroup of() {
        return new DtoMetaItemGroup();
    }

    /**
     * 引数として渡された {@code dtoMetaItemGroup} オブジェクトの情報を基に {@link DtoMetaItemGroup}
     * クラスの新しいインスタンスを生成し返却します。
     *
     * @param dtoMetaItemGroup DTOメタ項目グループ
     * @return {@link DtoMetaItemGroup} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static DtoMetaItemGroup of(@NonNull DtoMetaItemGroup dtoMetaItemGroup) {
        return new DtoMetaItemGroup(dtoMetaItemGroup);
    }

    /**
     * 引数として渡された {@code dtoMetaItem} を条件リストへ追加します。
     * <p>
     * この {@link DtoMetaItemGroup#add(DtoMetaItem)}
     * メソッドは自分自身のインスタンスを返却するため以下のようなメソッドチェーンでの操作が可能です。
     *
     * <pre>
     * <code>
     * DtoMetaItemGroup dtoMetaItemGroup = DtoMetaItemGroup.of()
     *                                  .add(dtoMetaItem1)
     *                                  .add(dtoMetaItem2);
     * </code>
     * </pre>
     *
     * @param dtoMetaItem DTOメタ項目
     * @return 自分自身のインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public DtoMetaItemGroup add(@NonNull DtoMetaItem dtoMetaItem) {
        this.dtoMetaItemGroup.add(dtoMetaItem);
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
     * {@link DtoMetaItem} を総称型として持つストリームを返却します。
     *
     * @return {@link DtoMetaItem} を総称型として持つストリーム
     */
    public Stream<DtoMetaItem> stream() {
        return this.dtoMetaItemGroup.stream();
    }

    @Override
    public List<DtoMetaItem> nodes() {
        return this.dtoMetaItemGroup;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Iterator<DtoMetaItem> iterator() {
        return FluentIterator.of(this);
    }
}