/**
 * Project Name : Generator<br>
 * File Name : DtoDefinitionItem.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/07/31<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.vo.dto;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * コンテンツ「DTO定義項目」の情報を管理するデータクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@Getter
@ToString
@EqualsAndHashCode
public final class DtoDefinitionItem implements Serializable {

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
    private DtoDefinitionItem() {
    }

    /**
     * コンストラクタ
     *
     * @param cellItemCode セル項目コード
     * @param cellItemName セル項目名
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private DtoDefinitionItem(int cellItemCode, @NonNull String cellItemName) {
        this.cellItemCode = cellItemCode;
        this.cellItemName = cellItemName;
    }

    /**
     * コピーコンストラクタ
     *
     * @param dtoDefinitionItem DTO定義項目
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private DtoDefinitionItem(@NonNull DtoDefinitionItem dtoDefinitionItem) {
        this.cellItemCode = dtoDefinitionItem.getCellItemCode();
        this.cellItemName = dtoDefinitionItem.getCellItemName();
    }

    /**
     * 引数として渡された情報を基に {@link DtoDefinitionItem} クラスの新しいインスタンスを生成し返却します。
     *
     * @param cellItemCode セル項目コード
     * @param cellItemName セル項目名
     * @return {@link DtoDefinitionItem} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static DtoDefinitionItem of(int cellItemCode, @NonNull String cellItemName) {
        return new DtoDefinitionItem(cellItemCode, cellItemName);
    }

    /**
     * 引数として渡された {@code dtoDefinitionItem} オブジェクトの情報を基に {@link DtoDefinitionItem}
     * クラスの新しいインスタンスを生成し返却します。
     *
     * @param cellItemCode セル項目コード
     * @param cellItemName セル項目名
     * @return {@link DtoDefinitionItem} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static DtoDefinitionItem of(@NonNull DtoDefinitionItem dtoDefinitionItem) {
        return new DtoDefinitionItem(dtoDefinitionItem);
    }
}