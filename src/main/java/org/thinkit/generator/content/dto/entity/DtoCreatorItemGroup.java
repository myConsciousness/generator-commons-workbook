/**
 * Project Name : Generator<br>
 * File Name : DtoCreatorItemGroup.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/06/13<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.vo.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import org.thinkit.common.util.iterator.FluentIterator;
import org.thinkit.common.util.iterator.IterableNode;

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
public final class DtoCreatorItemGroup implements Iterable<DtoCreatorItem>, IterableNode<DtoCreatorItem>, Serializable {

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