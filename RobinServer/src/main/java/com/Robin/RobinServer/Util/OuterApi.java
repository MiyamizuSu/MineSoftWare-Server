package com.Robin.RobinServer.Util;

import com.aliyun.auth.credentials.Credential;
import com.aliyun.auth.credentials.provider.StaticCredentialProvider;
import com.aliyun.sdk.service.dysmsapi20170525.AsyncClient;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsResponse;
import darabonba.core.client.ClientOverrideConfiguration;

import java.util.concurrent.CompletableFuture;

public class OuterApi {
    public static int AliCloudSms(String phoneNumber ,String accessKeyId, String accessKeySecret)  {
        try {
            StaticCredentialProvider provider = StaticCredentialProvider.create(Credential.builder()
                    .accessKeyId(accessKeyId)
                    .accessKeySecret(accessKeySecret)
                    .build());
            AsyncClient client = AsyncClient.builder()
                    .region("cn-hangzhou")
                    .credentialsProvider(provider)
                    .overrideConfiguration(
                            ClientOverrideConfiguration.create()
                                    .setEndpointOverride("dysmsapi.aliyuncs.com")
                    )
                    .build();

            SendSmsRequest sendSmsRequest = SendSmsRequest.builder()
                    .signName("阿里云短信测试")
                    .templateCode("SMS_154950909")
                    .phoneNumbers(phoneNumber)
                    .templateParam("{\"code\":\"1145\"}")
                    .build();
            CompletableFuture<SendSmsResponse> response = client.sendSms(sendSmsRequest);
            client.close();
            return 200;
        }
        catch (Exception e) {
            e.printStackTrace();
            return 500;
        }
    }
}
