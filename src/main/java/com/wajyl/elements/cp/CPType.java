package com.wajyl.elements.cp;

import com.wajyl.elements.ConstantPool;

public enum CPType {
    CP_UTF8_INFO(CPUtf8.class, 1),
    CP_INTEGER_INFO(CPInteger.class, 3),
    CP_FLOAT_INFO(CPFloat.class, 4),
    CP_LONG_INFO(CPLong.class, 5),
    CP_DOUBLE_INFO(CPDouble.class, 6),
    CP_CLASS_INFO(CPClass.class, 7),
    CP_STRING_INFO(CPString.class, 8),
    CP_FIELD_REF_INFO(CPFieldRef.class, 9),
    CP_METHOD_REF_INFO(CPMethodRef.class, 10),
    CP_INTERFACE_METHOD_REF_INFO(CPInterfaceMethodRef.class, 11),
    CP_NAMEANDTYPE_INFO(CPNameAndType.class, 12),
    CP_METHODHANDLE_INFO(CPMethodHandle.class, 15),
    CP_METHODTYPE_INFO(CPMethodType.class, 16),
    CP_INVOKE_DYNAMIC_INFO(CPInvokeDynamic.class, 18);

    private Class<? extends ConstantPool> clazz;
    private int tag;

    CPType(Class<? extends ConstantPool> clazz, int tag) {
        this.clazz = clazz;
        this.tag = tag;
    }

    public static Class<? extends ConstantPool> getClazz(int tag) {
        for (CPType cpType : CPType.values()) {
            if (cpType.getTag() == tag) {
                return cpType.getClazz();
            }
        }
        throw new UnsupportedOperationException("常量池类型不存在");
    }

    public Class<? extends ConstantPool> getClazz() {
        return clazz;
    }

    public int getTag() {
        return tag;
    }
}
