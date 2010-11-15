package com.tinkerpop.gremlin.compiler.types;

import com.tinkerpop.gremlin.compiler.context.GremlinScriptContext;
import com.tinkerpop.gremlin.compiler.operations.Operation;
import com.tinkerpop.gremlin.functions.Function;

import java.util.List;

/**
 * @author Pavel A. Yaskevich
 */
public class Func extends DynamicEntity {

    private final Function function;
    private List<Operation> arguments;
    private final GremlinScriptContext context;
    private boolean done = false;
    private Object fValue = null;

    public Func(final Function function, final List<Operation> arguments, final GremlinScriptContext context) {
        this.function = function;
        this.arguments = arguments;
        this.context = context;
    }

    protected Object value() {
        if (!done) {
            this.fValue = function.compute(this.arguments, this.context).getValue();
            this.done = true;
        }
        return fValue;
    }

    public Function getFunction() {
        return function;
    }

    public List<Operation> getArguments() {
        return arguments;
    }

    public GremlinScriptContext getContext() {
        return this.context;
    }

    public int hashCode() {
        return this.function.hashCode();
    }

}
