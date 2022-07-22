package com.surd.dp.asm;

import jdk.internal.org.objectweb.asm.ClassReader;
import jdk.internal.org.objectweb.asm.ClassVisitor;
import jdk.internal.org.objectweb.asm.FieldVisitor;
import jdk.internal.org.objectweb.asm.MethodVisitor;

import java.io.IOException;

import static jdk.internal.org.objectweb.asm.Opcodes.ASM4;

/**
 * @author admin
 * @date
 */
public class ClassPrinter extends ClassVisitor {
    public ClassPrinter() {
        super(ASM4);
    }

    public ClassPrinter(int i, ClassVisitor classVisitor) {
        super(i, classVisitor);
    }

    @Override
    public void visit(int i, int i1, String name, String s1, String superName, String[] strings) {
        System.out.println(name + " extends " + superName + " {");
    }

    @Override
    public FieldVisitor visitField(int i, String name, String desc, String s2, Object o) {
        System.out.println(" " + desc + " " + name);
        return null;
    }

    @Override
    public MethodVisitor visitMethod(int i, String name, String desc, String s2, String[] strings) {
        System.out.println(" " + name + desc);
        return null;
    }

    @Override
    public void visitEnd() {
            System.out.println("}");
    }

    public static void main(String[] args) throws IOException {
        ClassReader cr = new ClassReader("java.lang.Runnable");
        ClassPrinter cp = new ClassPrinter();
        cr.accept(cp,0);
    }
}
