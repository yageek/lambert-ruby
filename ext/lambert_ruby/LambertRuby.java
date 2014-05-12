package net.yageek.lambertruby;


import java.lang.Long;
import java.io.IOException;
import org.jruby.Ruby;
import org.jruby.RubyArray;
import org.jruby.RubyClass;
import org.jruby.RubyFixnum;
import org.jruby.RubyModule;
import org.jruby.RubyObject;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.load.BasicLibraryService;

public class LambertRuby implements BasicLibraryService {

  private Ruby runtime;

  public boolean basicLoad(Ruby runtime) throws IOException {
    this.runtime = runtime;

    RubyModule lambert = runtime.defineModule("Lambert");


    RubyClass lambertPoint = lambert.defineClassUnder("LambertPoint",runtime.getObject(), new ObjectAllocator() {

      public IRubyObject allocate(Ruby runtime,RubyClass rubyClass){

        return new Lambert(runtime, rubyClass);

  }

    });

       lambertPoint.defineAnnotatedMethods(LambertPoint.class);

       return true;
  }
    @JRubyMethod
      public IRubyObject mask(ThreadContext context, IRubyObject payload, IRubyObject mask) {

        RubyArray unmasked = RubyArray.newArray(runtime, n);


      return unmasked;
    }
  }
