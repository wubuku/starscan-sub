package org.starcoin.base;


public final class AccessPath {
    public final AccountAddress address;
    public final DataPath path;

    public AccessPath(AccountAddress address, DataPath path) {
        java.util.Objects.requireNonNull(address, "address must not be null");
        java.util.Objects.requireNonNull(path, "path must not be null");
        this.address = address;
        this.path = path;
    }

    public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        serializer.increase_container_depth();
        address.serialize(serializer);
        path.serialize(serializer);
        serializer.decrease_container_depth();
    }

    public byte[] bcsSerialize() throws com.novi.serde.SerializationError {
        com.novi.serde.Serializer serializer = new com.novi.bcs.BcsSerializer();
        serialize(serializer);
        return serializer.get_bytes();
    }

    public static AccessPath deserialize(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        deserializer.increase_container_depth();
        Builder builder = new Builder();
        builder.address = AccountAddress.deserialize(deserializer);
        builder.path = DataPath.deserialize(deserializer);
        deserializer.decrease_container_depth();
        return builder.build();
    }

    public static AccessPath bcsDeserialize(byte[] input) throws com.novi.serde.DeserializationError {
        if (input == null) {
             throw new com.novi.serde.DeserializationError("Cannot deserialize null array");
        }
        com.novi.serde.Deserializer deserializer = new com.novi.bcs.BcsDeserializer(input);
        AccessPath value = deserialize(deserializer);
        if (deserializer.get_buffer_offset() < input.length) {
             throw new com.novi.serde.DeserializationError("Some input bytes were not read");
        }
        return value;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        AccessPath other = (AccessPath) obj;
        if (!java.util.Objects.equals(this.address, other.address)) { return false; }
        if (!java.util.Objects.equals(this.path, other.path)) { return false; }
        return true;
    }

    public int hashCode() {
        int value = 7;
        value = 31 * value + (this.address != null ? this.address.hashCode() : 0);
        value = 31 * value + (this.path != null ? this.path.hashCode() : 0);
        return value;
    }

    public static final class Builder {
        public AccountAddress address;
        public DataPath path;

        public AccessPath build() {
            return new AccessPath(
                address,
                path
            );
        }
    }
}
