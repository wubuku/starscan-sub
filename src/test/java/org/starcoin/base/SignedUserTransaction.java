package org.starcoin.base;


public final class SignedUserTransaction {
    public final RawTransaction raw_txn;
    public final TransactionAuthenticator authenticator;

    public SignedUserTransaction(RawTransaction raw_txn, TransactionAuthenticator authenticator) {
        java.util.Objects.requireNonNull(raw_txn, "raw_txn must not be null");
        java.util.Objects.requireNonNull(authenticator, "authenticator must not be null");
        this.raw_txn = raw_txn;
        this.authenticator = authenticator;
    }

    public void serialize(com.novi.serde.Serializer serializer) throws com.novi.serde.SerializationError {
        serializer.increase_container_depth();
        raw_txn.serialize(serializer);
        authenticator.serialize(serializer);
        serializer.decrease_container_depth();
    }

    public byte[] bcsSerialize() throws com.novi.serde.SerializationError {
        com.novi.serde.Serializer serializer = new com.novi.bcs.BcsSerializer();
        serialize(serializer);
        return serializer.get_bytes();
    }

    public static SignedUserTransaction deserialize(com.novi.serde.Deserializer deserializer) throws com.novi.serde.DeserializationError {
        deserializer.increase_container_depth();
        Builder builder = new Builder();
        builder.raw_txn = RawTransaction.deserialize(deserializer);
        builder.authenticator = TransactionAuthenticator.deserialize(deserializer);
        deserializer.decrease_container_depth();
        return builder.build();
    }

    public static SignedUserTransaction bcsDeserialize(byte[] input) throws com.novi.serde.DeserializationError {
        if (input == null) {
             throw new com.novi.serde.DeserializationError("Cannot deserialize null array");
        }
        com.novi.serde.Deserializer deserializer = new com.novi.bcs.BcsDeserializer(input);
        SignedUserTransaction value = deserialize(deserializer);
        if (deserializer.get_buffer_offset() < input.length) {
             throw new com.novi.serde.DeserializationError("Some input bytes were not read");
        }
        return value;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        SignedUserTransaction other = (SignedUserTransaction) obj;
        if (!java.util.Objects.equals(this.raw_txn, other.raw_txn)) { return false; }
        if (!java.util.Objects.equals(this.authenticator, other.authenticator)) { return false; }
        return true;
    }

    public int hashCode() {
        int value = 7;
        value = 31 * value + (this.raw_txn != null ? this.raw_txn.hashCode() : 0);
        value = 31 * value + (this.authenticator != null ? this.authenticator.hashCode() : 0);
        return value;
    }

    public static final class Builder {
        public RawTransaction raw_txn;
        public TransactionAuthenticator authenticator;

        public SignedUserTransaction build() {
            return new SignedUserTransaction(
                raw_txn,
                authenticator
            );
        }
    }
}
