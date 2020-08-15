/**
 * Project Name : Generator<br>
 * File Name : DtoMetaItem.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/07/31<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.content.dto.entity;

import java.io.Serializable;

import org.thinkit.framework.content.entity.ContentEntity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * コンテンツ「DTOメタ項目」の情報を管理するデータクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@Getter
@ToString
@EqualsAndHashCode
public final class DtoMetaItem implements ContentEntity, Serializable {

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = 8717094695982939096L;

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
    private DtoMetaItem() {
    }

    /**
     * コンストラクタ
     *
     * @param cellItemCode セル項目コード
     * @param cellItemName セル項目名
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private DtoMetaItem(int cellItemCode, @NonNull String cellItemName) {
        this.cellItemCode = cellItemCode;
        this.cellItemName = cellItemName;
    }

    /**
     * コピーコンストラクタ
     *
     * @param dtoMetaItem DTOメタ項目
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private DtoMetaItem(@NonNull DtoMetaItem dtoMetaItem) {
        this.cellItemCode = dtoMetaItem.getCellItemCode();
        this.cellItemName = dtoMetaItem.getCellItemName();
    }

    /**
     * 引数として渡された情報を基に {@link DtoMetaItem} クラスの新しいインスタンスを生成し返却します。
     *
     * @param cellItemCode セル項目コード
     * @param cellItemName セル項目名
     * @return {@link DtoMetaItem} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static DtoMetaItem of(int cellItemCode, @NonNull String cellItemName) {
        return new DtoMetaItem(cellItemCode, cellItemName);
    }

    /**
     * 引数として渡された {@code dtoMetaItem} オブジェクトの情報を基に {@link DtoMetaItem}
     * クラスの新しいインスタンスを生成し返却します。
     *
     * @param cellItemCode セル項目コード
     * @param cellItemName セル項目名
     * @return {@link DtoMetaItem} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static DtoMetaItem of(@NonNull DtoMetaItem dtoMetaItem) {
        return new DtoMetaItem(dtoMetaItem);
    }
}