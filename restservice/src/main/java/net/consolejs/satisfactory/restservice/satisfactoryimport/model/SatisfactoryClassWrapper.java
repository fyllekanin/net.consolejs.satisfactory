package net.consolejs.restservice.satisfactoryimport.model;

import com.google.gson.annotations.SerializedName;
import net.consolejs.entityview.satisfactory.NativeClass;

import java.util.List;
import java.util.Objects;

public class SatisfactoryClassWrapper {
    @SerializedName("NativeClass")
    private final NativeClass myNativeClass;
    @SerializedName("Classes")
    private final List<SatisfactoryClass> myClasses;

    private SatisfactoryClassWrapper(Builder builder) {
        myNativeClass = builder.myNativeClass;
        myClasses = builder.myClasses;
    }

    public NativeClass getNativeClass() {
        return myNativeClass;
    }

    public List<SatisfactoryClass> getClasses() {
        return myClasses;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Builder newBuilderFromCurrent() {
        return new Builder(this);
    }

    public static class Builder {
        private NativeClass myNativeClass;
        private List<SatisfactoryClass> myClasses;

        private Builder() {
            // Empty
        }

        private Builder(SatisfactoryClassWrapper original) {
            myNativeClass = original.myNativeClass;
            myClasses = original.myClasses;
        }

        public Builder withNativeClass(NativeClass nativeClass) {
            myNativeClass = nativeClass;
            return this;
        }

        public Builder withClasses(List<SatisfactoryClass> classes) {
            myClasses = classes;
            return this;
        }

        public SatisfactoryClassWrapper build() {
            return new SatisfactoryClassWrapper(this);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        SatisfactoryClassWrapper other = (SatisfactoryClassWrapper) obj;

        return Objects.equals(getNativeClass(), other.getNativeClass()) &&
                Objects.equals(getClasses(), other.getClasses());
    }
}
