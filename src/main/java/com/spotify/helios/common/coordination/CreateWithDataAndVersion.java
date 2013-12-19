package com.spotify.helios.common.coordination;

import com.netflix.curator.framework.api.transaction.CuratorTransaction;

import java.util.Arrays;

class CreateWithDataAndVersion implements ZooKeeperOperation {

  private final String path;
  private final byte[] data;
  private final int version;

  CreateWithDataAndVersion(final String path, final byte[] data, final int version) {
    this.path = path;
    this.data = data;
    this.version = version;
  }

  @Override
  public void register(final CuratorTransaction transaction) throws Exception {
    transaction
        .create().forPath(path).and()
        .setData().withVersion(version).forPath(path, data);
  }

  @Override
  public String toString() {
    return "CreateWithDataAndVersion{" +
           "path='" + path + '\'' +
           ", data=" + Arrays.toString(data) +
           ", version=" + version +
           '}';
  }
}