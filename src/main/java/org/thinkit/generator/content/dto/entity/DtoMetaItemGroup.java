/**
 * Project Name : Generator<br>
 * File Name : DtoMetaItemGroup.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/06/13<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
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