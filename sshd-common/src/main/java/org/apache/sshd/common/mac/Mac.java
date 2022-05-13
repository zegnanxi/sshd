/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.sshd.common.mac;

import org.apache.sshd.common.util.NumberUtils;

/**
 * Message Authentication Code for use in SSH.
 * It usually wraps a javax.crypto.Mac class.
 *
 * @author <a href="mailto:dev@mina.apache.org">Apache MINA SSHD Project</a>
 */
public interface Mac extends MacInformation {
    void init(byte[] key) throws Exception;

    default void update(byte[] buf) {
        update(buf, 0, NumberUtils.length(buf));
    }

    void update(byte[] buf, int start, int len);

    void updateUInt(long foo);

    default byte[] doFinal() throws Exception {
        int blockSize = getBlockSize();
        byte[] buf = new byte[blockSize];
        doFinal(buf);
        return buf;
    }

    default void doFinal(byte[] buf) throws Exception {
        doFinal(buf, 0);
    }

    void doFinal(byte[] buf, int offset) throws Exception;
}
