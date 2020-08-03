/**
 * Project Name : Generator<br>
 * File Name : ContentMetaItem.java<br>
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

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * コンテンツ「コンテンツメタ項目」の情報を管理するデータクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@Getter
@ToString
@EqualsAndHashCode
public final class ContentMetaItem implements Serializable {

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = -8966026188033536036L;

    /**
     * セル項目コード
     */
    private int cellItemCode;

    /**
     * セル項目名
     */
    private String cellItemName;

    /**
     * デフォルトコンストラクタ
     */
    private ContentMetaItem() {
    }

    /**
     * コンストラクタ
     *
     * @param cellItemCode セル項目コード
     * @param cellItemName セル項目名
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private ContentMetaItem(int cellItemCode, @NonNull String cellItemName) {
        this.cellItemCode = cellItemCode;
        this.cellItemName = cellItemName;
    }

    /**
     * コピーコンストラクタ
     *
     * @param contentMetaItem コンテンツメタ項目
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private ContentMetaItem(@NonNull ContentMetaItem contentMetaItem) {
        this.cellItemCode = contentMetaItem.getCellItemCode();
        this.cellItemName = contentMetaItem.getCellItemName();
    }

    /**
     * 引数として渡された情報を基に {@link ContentMetaItem} クラスの新しいインスタンスを生成し返却します。
     *
     * @param cellItemCode セル項目コード
     * @param cellItemName セル項目名
     * @return {@link ContentMetaItem} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static ContentMetaItem of(int cellItemCode, @NonNull String cellItemName) {
        return new ContentMetaItem(cellItemCode, cellItemName);
    }

    /**
     * 引数として渡された {@code contentMetaItem} オブジェクトの情報を基に {@link ContentMetaItem}
     * クラスの新しいインスタンスを生成し返却します。
     *
     * @param contentMetaItem コンテンツメタ項目
     * @return {@link ContentMetaItem} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static ContentMetaItem of(@NonNull ContentMetaItem contentMetaItem) {
        return new ContentMetaItem(contentMetaItem);
    }
}