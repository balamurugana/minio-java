/*
 * MinIO Java SDK for Amazon S3 Compatible Cloud Storage, (C) 2020 MinIO, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.minio.credentials;

import java.util.function.Supplier;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import okhttp3.OkHttpClient;

/**
 * Credential provider using <a
 * href="https://docs.aws.amazon.com/STS/latest/APIReference/API_AssumeRoleWithWebIdentity.html">AssumeRoleWithWebIdentity
 * API</a>.
 */
public class WebIdentityProvider extends WebIdentityClientGrantsProvider {
  private final String roleArn;
  private final String roleSessionName;

  public WebIdentityProvider(
      @Nonnull Supplier<Jwt> supplier,
      @Nonnull String stsEndpoint,
      @Nullable Integer durationSeconds,
      @Nullable String policy,
      @Nullable String roleArn,
      @Nullable String roleSessionName,
      @Nullable OkHttpClient customHttpClient) {
    super(supplier, stsEndpoint, durationSeconds, policy, customHttpClient);
    this.roleArn = roleArn;
    this.roleSessionName = roleSessionName;
  }

  protected boolean isWebIdentity() {
    return true;
  }

  @Override
  protected String roleArn() {
    return roleArn;
  }

  @Override
  protected String roleSessionName() {
    return (roleSessionName != null) ? roleSessionName : String.valueOf(System.currentTimeMillis());
  }
}