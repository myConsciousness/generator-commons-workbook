/**
 * Project Name : Generator<br>
 * File Name : ContentVersionItemGroup.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/08/03<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.vo.content;

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
 * コンテンツ「コンテンツバージョン項目」のオブジェクトグループを管理するデータクラスです。
 * <p>
 * このクラスはFluentインターフェースの概念を応用し設計されています。<br>
 * そのため、以下のようなメソッドチェーンでの操作が可能です。
 *
 * <pre>
 * <code>
 * ContentVersionItemGroup contentVersionItemGroup = ContentVersionItemGroup.of()
 *                                  .add(contentVersionItem1)
 *                                  .add(contentVersionItem2);
 * </code>
 * </pre>
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode
public final class ContentVersionItemGroup
        implements Iterable<ContentVersionItem>, IterableNode<ContentVersionItem>, Serializable {

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = 5501189774410411268L;

    /**
     * コンテンツバージョン項目グループ
     */
    @Getter
    private List<ContentVersionItem> contentVersionItemGroup;

    /**
     * コンテンツバージョン項目グループのサイズ
     */
    private int size;

    /**
     * デフォルトコンストラクタ
     */
    private ContentVersionItemGroup() {
        this.contentVersionItemGroup = new ArrayList<>(0);
        this.size = 0;
    }

    /**
     * コピーコンストラクタ
     *
     * @param contentVersionItemGroup コンテンツバージョン項目グループ
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private ContentVersionItemGroup(@NonNull ContentVersionItemGroup contentVersionItemGroup) {
        this.contentVersionItemGroup = new ArrayList<>(contentVersionItemGroup.getContentVersionItemGroup());
    }

    /**
     * {@link ContentVersionItemGroup} クラスの新しいインスタンスを生成し返却します。
     *
     * @return {@link ContentVersionItemGroup} クラスの新しいインスタンス
     */
    public static ContentVersionItemGroup of() {
        return new ContentVersionItemGroup();
    }

    /**
     * 引数として渡された {@code contentVersionItemGroup} オブジェクトの情報を基に
     * {@link ContentVersionItemGroup} クラスの新しいインスタンスを生成し返却します。
     *
     * @param contentVersionItemGroup コンテンツバージョン項目グループ
     * @return {@link ContentVersionItemGroup} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static ContentVersionItemGroup of(@NonNull ContentVersionItemGroup contentVersionItemGroup) {
        return new ContentVersionItemGroup(contentVersionItemGroup);
    }

    /**
     * 引数として渡された {@code contentVersionItem} を条件リストへ追加します。
     * <p>
     * この {@link ContentVersionItemGroup#add(ContentVersionItem)}
     * メソッドは自分自身のインスタンスを返却するため以下のようなメソッドチェーンでの操作が可能です。
     *
     * <pre>
     * <code>
     * ContentVersionItemGroup contentVersionItemGroup = ContentVersionItemGroup.of()
     *                                  .add(contentVersionItem1)
     *                                  .add(contentVersionItem2);
     * </code>
     * </pre>
     *
     * @param ContentVersionItem コンテンツバージョン項目
     * @return 自分自身のインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public ContentVersionItemGroup add(@NonNull ContentVersionItem ContentVersionItem) {
        this.contentVersionItemGroup.add(ContentVersionItem);
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
     * {@link ContentVersionItem} クラスを総称型として持つストリームを返却します。
     *
     * @return {@link ContentVersionItem} クラスを総称型として持つストリーム
     */
    public Stream<ContentVersionItem> stream() {
        return this.contentVersionItemGroup.stream();
    }

    @Override
    public List<ContentVersionItem> nodes() {
        return this.contentVersionItemGroup;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public Iterator<ContentVersionItem> iterator() {
        return FluentIterator.of(this);
    }
}