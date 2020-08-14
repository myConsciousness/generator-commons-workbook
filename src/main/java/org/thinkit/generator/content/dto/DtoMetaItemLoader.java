/**
 * Project Name : Generator<br>
 * File Name : DtoMetaItemLoader.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/05/16<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */
package org.thinkit.generator.rule.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.thinkit.common.rule.Attribute;
import org.thinkit.common.rule.Condition;
import org.thinkit.common.rule.Content;
import org.thinkit.common.rule.Rule;
import org.thinkit.generator.vo.dto.DtoMetaItem;
import org.thinkit.generator.vo.dto.DtoMetaItemGroup;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * コンテンツ「DTOメタ項目」の情報をロードするルールクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode
public final class DtoMetaItemLoader implements Rule<DtoMetaItemGroup> {

    /**
     * デフォルトコンストラクタ
     */
    private DtoMetaItemLoader() {
    }

    /**
     * {@link DtoMetaItemLoader} クラスの新しいインスタンスを生成し返却します。
     *
     * @return {@link DtoMetaItemLoader} クラスの新しいインスタンス
     */
    public static Rule<DtoMetaItemGroup> of() {
        return new DtoMetaItemLoader();
    }

    /**
     * コンテンツ名定数
     */
    private enum ContentName implements Content {
        DTOメタ項目;

        @Override
        public String getString() {
            return this.name();
        }
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
    public DtoMetaItemGroup execute() {

        final DtoMetaItemGroup dtoMetaItemGroup = DtoMetaItemGroup.of();

        loadContent(ContentName.DTOメタ項目).forEach(content -> {
            dtoMetaItemGroup.add(DtoMetaItem.of(Integer.parseInt(content.get(ContentAttribute.セル項目コード.getString())),
                    content.get(ContentAttribute.セル項目名.getString())));
        });

        return dtoMetaItemGroup;
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