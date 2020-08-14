/**
 * Project Name : Generator<br>
 * File Name : ContentVersionItem.java<br>
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
 * コンテンツ「コンテンツバージョン項目」の情報を管理するデータクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@Getter
@ToString
@EqualsAndHashCode
public final class ContentVersionItem implements Serializable {

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = 1877181825928961322L;

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
    private ContentVersionItem() {
    }

    /**
     * コンストラクタ
     *
     * @param cellItemCode セル項目コード
     * @param cellItemName セル項目名
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private ContentVersionItem(int cellItemCode, @NonNull String cellItemName) {
        this.cellItemCode = cellItemCode;
        this.cellItemName = cellItemName;
    }

    /**
     * コピーコンストラクタ
     *
     * @param contentVersionItem コンテンツバージョン項目
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private ContentVersionItem(@NonNull ContentVersionItem contentVersionItem) {
        this.cellItemCode = contentVersionItem.getCellItemCode();
        this.cellItemName = contentVersionItem.getCellItemName();
    }

    /**
     * 引数として渡された情報を基に {@link ContentVersionItem} クラスの新しいインスタンスを生成し返却します。
     *
     * @param cellItemCode セル項目コード
     * @param cellItemName セル項目名
     * @return {@link ContentVersionItem} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static ContentVersionItem of(int cellItemCode, @NonNull String cellItemName) {
        return new ContentVersionItem(cellItemCode, cellItemName);
    }

    /**
     * 引数として渡された {@code contentVersionItem} オブジェクトの情報を基に {@link ContentVersionItem}
     * クラスの新しいインスタンスを生成し返却します。
     *
     * @param contentVersionItem コンテンツバージョン項目
     * @return {@link ContentVersionItem} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static ContentVersionItem of(@NonNull ContentVersionItem contentVersionItem) {
        return new ContentVersionItem(contentVersionItem);
    }
}