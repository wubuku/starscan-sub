package org.starcoin.base;


public final class AddLiquidityEvent {
    public final java.math.@com.novi.serde.Unsigned @com.novi.serde.Int128 BigInteger liquidity;
    public final TokenCode x_token_code;
    public final TokenCode y_token_code;
    public final AccountAddress signer;

    public AddLiquidityEvent(java.math.@com.novi.serde.Unsigned @com.novi.serde.Int128 BigInteger liquidity, TokenCode x_token_code, TokenCode y_token_code, AccountAddress signer) {
        java.util.Objects.requireNonNull(liquidity, "liquidity must not be null");
        java.util.Objects.requireNonNull(x_token_code, "x_token_code must not be null");
        java.util.Objects.requireNonNull(y_token_code, "y_token_code must not be null");
        java.util.Objects.requireNonNull(signer, "signer must not be null");
        this.liquidity = liquidity;
        this.x_token_code = x_token_code;
        this.y_token_code = y_token_code;
        this.signer = signer;
    }

    public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        serializer.increase_container_depth();
        serializer.serialize_u128(liquidity);
        x_token_code.serialize(serializer);
        y_token_code.serialize(serializer);
        signer.serialize(serializer);
        serializer.decrease_container_depth();
    }

    public byte[] bcsSerialize() throws com.novi.serde.SerializationError {
        com.novi.serde.Serializer serializer = new com.novi.bcs.BcsSerializer();
        serialize(serializer);
        return serializer.get_bytes();
    }

    public static AddLiquidityEvent deserialize(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        deserializer.increase_container_depth();
        Builder builder = new Builder();
        builder.liquidity = deserializer.deserialize_u128();
        builder.x_token_code = TokenCode.deserialize(deserializer);
        builder.y_token_code = TokenCode.deserialize(deserializer);
        builder.signer = AccountAddress.deserialize(deserializer);
        deserializer.decrease_container_depth();
        return builder.build();
    }

    public static AddLiquidityEvent bcsDeserialize(byte[] input) throws com.novi.serde.DeserializationError {
        if (input == null) {
             throw new com.novi.serde.DeserializationError("Cannot deserialize null array");
        }
        com.novi.serde.Deserializer deserializer = new com.novi.bcs.BcsDeserializer(input);
        AddLiquidityEvent value = deserialize(deserializer);
        if (deserializer.get_buffer_offset() < input.length) {
             throw new com.novi.serde.DeserializationError("Some input bytes were not read");
        }
        return value;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        AddLiquidityEvent other = (AddLiquidityEvent) obj;
        if (!java.util.Objects.equals(this.liquidity, other.liquidity)) { return false; }
        if (!java.util.Objects.equals(this.x_token_code, other.x_token_code)) { return false; }
        if (!java.util.Objects.equals(this.y_token_code, other.y_token_code)) { return false; }
        if (!java.util.Objects.equals(this.signer, other.signer)) { return false; }
        return true;
    }

    public int hashCode() {
        int value = 7;
        value = 31 * value + (this.liquidity != null ? this.liquidity.hashCode() : 0);
        value = 31 * value + (this.x_token_code != null ? this.x_token_code.hashCode() : 0);
        value = 31 * value + (this.y_token_code != null ? this.y_token_code.hashCode() : 0);
        value = 31 * value + (this.signer != null ? this.signer.hashCode() : 0);
        return value;
    }

    public static final class Builder {
        public java.math.@com.novi.serde.Unsigned @com.novi.serde.Int128 BigInteger liquidity;
        public TokenCode x_token_code;
        public TokenCode y_token_code;
        public AccountAddress signer;

        public AddLiquidityEvent build() {
            return new AddLiquidityEvent(
                liquidity,
                x_token_code,
                y_token_code,
                signer
            );
        }
    }
}
