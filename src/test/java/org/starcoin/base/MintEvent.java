package org.starcoin.base;


public final class MintEvent {
    public final java.math.@com.novi.serde.Unsigned @com.novi.serde.Int128 BigInteger amount;
    public final TokenCode token_code;

    public MintEvent(java.math.@com.novi.serde.Unsigned @com.novi.serde.Int128 BigInteger amount, TokenCode token_code) {
        java.util.Objects.requireNonNull(amount, "amount must not be null");
        java.util.Objects.requireNonNull(token_code, "token_code must not be null");
        this.amount = amount;
        this.token_code = token_code;
    }

    public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        serializer.increase_container_depth();
        serializer.serialize_u128(amount);
        token_code.serialize(serializer);
        serializer.decrease_container_depth();
    }

    public byte[] bcsSerialize() throws com.novi.serde.SerializationError {
        com.novi.serde.Serializer serializer = new com.novi.bcs.BcsSerializer();
        serialize(serializer);
        return serializer.get_bytes();
    }

    public static MintEvent deserialize(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        deserializer.increase_container_depth();
        Builder builder = new Builder();
        builder.amount = deserializer.deserialize_u128();
        builder.token_code = TokenCode.deserialize(deserializer);
        deserializer.decrease_container_depth();
        return builder.build();
    }

    public static MintEvent bcsDeserialize(byte[] input) throws com.novi.serde.DeserializationError {
        if (input == null) {
             throw new com.novi.serde.DeserializationError("Cannot deserialize null array");
        }
        com.novi.serde.Deserializer deserializer = new com.novi.bcs.BcsDeserializer(input);
        MintEvent value = deserialize(deserializer);
        if (deserializer.get_buffer_offset() < input.length) {
             throw new com.novi.serde.DeserializationError("Some input bytes were not read");
        }
        return value;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        MintEvent other = (MintEvent) obj;
        if (!java.util.Objects.equals(this.amount, other.amount)) { return false; }
        if (!java.util.Objects.equals(this.token_code, other.token_code)) { return false; }
        return true;
    }

    public int hashCode() {
        int value = 7;
        value = 31 * value + (this.amount != null ? this.amount.hashCode() : 0);
        value = 31 * value + (this.token_code != null ? this.token_code.hashCode() : 0);
        return value;
    }

    public static final class Builder {
        public java.math.@com.novi.serde.Unsigned @com.novi.serde.Int128 BigInteger amount;
        public TokenCode token_code;

        public MintEvent build() {
            return new MintEvent(
                amount,
                token_code
            );
        }
    }
}
