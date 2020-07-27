/**
 * Project Name : Generator<br>
 * File Name : ClassNameDefinitionReader.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/05/16<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.rule.dtogenerator;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import com.google.common.flogger.FluentLogger;

import org.thinkit.common.rule.AbstractRule;
import org.thinkit.common.rule.Attribute;
import org.thinkit.common.rule.Content;

import lombok.EqualsAndHashCode;

import lombok.ToString;

/**
 * Excelに記述された定義書シートからクラス名定義情報を抽出する処理を行うルールです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public final class ClassNameDefinitionLoader extends AbstractRule<List<Map<String, String>>> {

    /**
     * ログ出力オブジェクト
     */
    private static final FluentLogger logger = FluentLogger.forEnclosingClass();

    /**
     * デフォルトコンストラクタ
     */
    public ClassNameDefinitionLoader() {
        super.loadContent(ContentName.クラス名定義情報);
    }

    /**
     * コンテンツ名定数
     */
    private enum ContentName implements Content {
        クラス名定義情報;

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
    public List<Map<String, String>> execute() {
        return super.getContents();
    }

    @Override
    protected List<Attribute> getAttributes() {

        final List<Attribute> attributes = new ArrayList<>(2);
        attributes.add(ContentAttribute.セル項目コード);
        attributes.add(ContentAttribute.セル項目名);

        logger.atInfo().log("クラス名定義情報のアトリビュート = (%s)", attributes);
        return attributes;
    }
}
