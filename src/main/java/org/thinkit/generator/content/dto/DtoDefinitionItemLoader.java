/**
 * Project Name : Generator<br>
 * File Name : DtoDefinitionItemLoader.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/05/24<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */
package org.thinkit.generator.content.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.thinkit.framework.content.Attribute;
import org.thinkit.framework.content.Condition;
import org.thinkit.framework.content.Content;
import org.thinkit.framework.content.annotation.ContentMapping;
import org.thinkit.generator.content.dto.entity.DtoDefinitionItem;
import org.thinkit.generator.content.dto.entity.DtoDefinitionItemGroup;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * コンテンツ「クラス定義セル」の情報をロードするルールクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode
@ContentMapping(content = "DTO定義項目")
public final class DtoDefinitionItemLoader implements Content<DtoDefinitionItemGroup> {

    /**
     * デフォルトコンストラクタ
     */
    private DtoDefinitionItemLoader() {
    }

    /**
     * {@link DtoDefinitionItemLoader} クラスの新しいインスタンスを生成し返却します。
     *
     * @return {@link DtoDefinitionItemLoader} クラスの新しいインスタンス
     */
    public static Content<DtoDefinitionItemGroup> of() {
        return new DtoDefinitionItemLoader();
    }

    /**
     * コンテンツ要素定数
     */
    private enum ContentAttribute implements Attribute {
        セル項目コード, セル項目名;

        @Override
        public String getString() {
            return this.name();
        }
    }

    @Override
    public DtoDefinitionItemGroup execute() {

        final DtoDefinitionItemGroup dtoDefinitionItemGroup = DtoDefinitionItemGroup.of();

        loadContent(this.getClass()).forEach(content -> {
            dtoDefinitionItemGroup
                    .add(DtoDefinitionItem.of(Integer.parseInt(content.get(ContentAttribute.セル項目コード.getString())),
                            content.get(ContentAttribute.セル項目名.getString())));
        });

        return dtoDefinitionItemGroup;

    }

    @Override
    public List<Attribute> getAttributes() {
        final List<Attribute> attributes = new ArrayList<>(2);
        attributes.add(ContentAttribute.セル項目コード);
        attributes.add(ContentAttribute.セル項目名);

        return attributes;
    }

    @Override
    public Map<Condition, String> getConditions() {
        return null;
    }
}