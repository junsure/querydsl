/*
 * Copyright (c) 2009 Mysema Ltd.
 * All rights reserved.
 * 
 */
package com.mysema.query.types.expr;

import com.mysema.commons.lang.Assert;
import com.mysema.query.types.Visitor;


/**
 * ExprConst represents general constant expressions
 * 
 * @author tiwe
 * 
 * @param <D> Java type of constant
 */
public class ExprConst<D> extends Expr<D> implements Constant<D> {

    private static final long serialVersionUID = -3211963259241932307L;

    /**
     * Factory method for constants
     * 
     * @param <D>
     * @param val
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> Expr<T> create(T val){
        if (val instanceof Boolean){
            return (Expr<T>)EBooleanConst.create((Boolean)val);            
        }else{
            return new ExprConst<T>(Assert.notNull(val,"val is null"));    
        }
    }
    
    private final D constant;

    @SuppressWarnings("unchecked")
    ExprConst(D constant) {
        super((Class<D>) constant.getClass());
        this.constant = constant;
    }
        
    @Override
    public int hashCode() {
        return constant.hashCode();
    }
    
    /**
     * Get the embedded constant
     * 
     * @return
     */
    @Override
    public D getConstant() {
        return constant;
    }

    @Override
    public EBoolean eq(D s){
        return EBooleanConst.create(constant.equals(s));
    }
    
    @Override
    public EBoolean ne(D s){
        return EBooleanConst.create(!constant.equals(s));
    }
    
    @Override
    public void accept(Visitor v) {
        v.visit(this);        
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object o) {
        if (o == this){
            return true;
        }else if (o instanceof Constant){
            return ((Constant)o).getConstant().equals(constant);
        }else{
            return false;
        }
    }
    
}