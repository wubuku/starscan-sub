package org.starcoin.base;


public final class WithdrawCapabilityResource {
    public final AccountAddress account_address;

    public WithdrawCapabilityResource(AccountAddress account_address) {
        java.util.Objects.requireNonNull(account_address, "account_address must not be null");
        this.account_address = account_address;
    }

    public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        serializer.increase_container_depth();
        account_address.serialize(serializer);
        serializer.decrease_container_depth();
    }

    public byte[] bcsSerialize() throws com.novi.serde.SerializationError {
        com.novi.serde.Serializer serializer = new com.novi.bcs.BcsSerializer();
        serialize(serializer);
        return serializer.get_bytes();
    }

    public static WithdrawCapabilityResource deserialize(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        deserializer.increase_container_depth();
        Builder builder = new Builder();
        builder.account_address = AccountAddress.deserialize(deserializer);
        deserializer.decrease_container_depth();
        return builder.build();
    }

    public static WithdrawCapabilityResource bcsDeserialize(byte[] input) throws com.novi.serde.DeserializationError {
        if (input == null) {
             throw new com.novi.serde.DeserializationError("Cannot deserialize null array");
        }
        com.novi.serde.Deserializer deserializer = new com.novi.bcs.BcsDeserializer(input);
        WithdrawCapabilityResource value = deserialize(deserializer);
        if (deserializer.get_buffer_offset() < input.length) {
             throw new com.novi.serde.DeserializationError("Some input bytes were not read");
        }
        return value;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        WithdrawCapabilityResource other = (WithdrawCapabilityResource) obj;
        if (!java.util.Objects.equals(this.account_address, other.account_address)) { return false; }
        return true;
    }

    public int hashCode() {
        int value = 7;
        value = 31 * value + (this.account_address != null ? this.account_address.hashCode() : 0);
        return value;
    }

    public static final class Builder {
        public AccountAddress account_address;

        public WithdrawCapabilityResource build() {
            return new WithdrawCapabilityResource(
                account_address
            );
        }
    }
}
