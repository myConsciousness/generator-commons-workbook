/**
 * Project Name : Generator<br>
 * File Name : DefaultOutputPath.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/07/27<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.dto;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * 各生成器で生成したリソースを出力する際の既定出力先を管理するデータクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode
public final class DefaultOutputPath implements Serializable {

    /**
     * シリアルバージョンUID
     */
    private static final long serialVersionUID = -2055490266485795899L;

    /**
     * 環境変数名
     */
    @Getter
    private String environmentVariableName;

    /**
     * 出力先ディレクトリ
     */
    @Getter
    private String outputDirectory;

    /**
     * デフォルトコンストラクタ
     */
    private DefaultOutputPath() {
    }

    /**
     * コンストラクタ
     *
     * @param environmentVariableName 環境変数名
     * @param outputDirectory         出力先ディレクトリ
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private DefaultOutputPath(@NonNull String environmentVariableName, @NonNull String outputDirectory) {
        this.environmentVariableName = environmentVariableName;
        this.outputDirectory = outputDirectory;
    }

    /**
     * コピーコンストラクタ
     *
     * @param defaultOutputPath 既定出力先オブジェクト
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    private DefaultOutputPath(@NonNull DefaultOutputPath defaultOutputPath) {
        this.environmentVariableName = defaultOutputPath.getEnvironmentVariableName();
        this.outputDirectory = defaultOutputPath.getOutputDirectory();
    }

    /**
     * 引数として指定された情報を基に {@link DefaultOutputPath} クラスの新しいインスタンスを生成し返却します。
     *
     * @param environmentVariableName 環境変数名
     * @param outputDirectory         出力先ディレクトリ
     * @return {@link DefaultOutputPath} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static DefaultOutputPath of(@NonNull String environmentVariableName, @NonNull String outputDirectory) {
        return new DefaultOutputPath(environmentVariableName, outputDirectory);
    }

    /**
     * 引数として指定された {@code defaultOutputPath} オブジェクトの情報を基に {@link DefaultOutputPath}
     * クラスの新しいインスタンスを生成し返却します。
     *
     * @param defaultOutputPath 既定出力先オブジェクト
     * @return {@link DefaultOutputPath} クラスの新しいインスタンス
     *
     * @exception NullPointerException 引数として {@code null} が渡された場合
     */
    public static DefaultOutputPath of(@NonNull DefaultOutputPath defaultOutputPath) {
        return new DefaultOutputPath(defaultOutputPath);
    }
}