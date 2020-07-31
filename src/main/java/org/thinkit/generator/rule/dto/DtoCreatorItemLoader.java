/**
 * Project Name : Generator<br>
 * File Name : DtoCreatorItemLoader.java<br>
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
import org.thinkit.generator.vo.dto.DtoCreatorItem;
import org.thinkit.generator.vo.dto.DtoCreatorItemGroup;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * コンテンツ「DTO作成者項目」の情報をロードするルールクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public final class DtoCreatorItemLoader implements Rule<DtoCreatorItemGroup> {

    /**
     * デフォルトコンストラクタ
     */
    private DtoCreatorItemLoader() {
    }

    /**
     * {@link DtoCreatorItemLoader} クラスの新しいインスタンスを生成し返却します。
     *
     * @return {@link DtoCreatorItemLoader} クラスの新しいインスタンス
     */
    public static DtoCreatorItemLoader of() {
        return new DtoCreatorItemLoader();
    }

    /**
     * コンテンツ名定数
     */
    private enum ContentName implements Content {
        DTO作成者項目;

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
    public DtoCreatorItemGroup execute() {

        final DtoCreatorItemGroup dtoCreatorItemGroup = DtoCreatorItemGroup.of();

        loadContent(ContentName.DTO作成者項目).forEach(content -> {
            dtoCreatorItemGroup
                    .add(DtoCreatorItem.of(Integer.parseInt(content.get(ContentAttribute.セル項目コード.getString())),
                            content.get(ContentAttribute.セル項目名.getString())));

        });

        return dtoCreatorItemGroup;
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
