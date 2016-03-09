package com.yageek.lambertruby;


import java.lang.Long;
import java.io.IOException;
import org.jruby.Ruby;
import org.jruby.RubyArray;
import org.jruby.RubyClass;
import org.jruby.RubyFixnum;
import org.jruby.RubyFloat;
import org.jruby.RubyModule;
import org.jruby.RubyObject;
import org.jruby.anno.JRubyMethod;
import org.jruby.anno.JRubyClass;
import org.jruby.runtime.ObjectAllocator;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.load.BasicLibraryService;

public class LambertRubyService implements BasicLibraryService {

  private Ruby runtime;

  public boolean basicLoad(Ruby runtime) throws IOException {
    this.runtime = runtime;

    RubyModule lambert = runtime.defineModule("Lambert");

    lambert.defineConstant("LambertI", runtime.newFixnum(0));
    lambert.defineConstant("LambertII", runtime.newFixnum(1));
    lambert.defineConstant("LambertIII", runtime.newFixnum(2));
    lambert.defineConstant("LambertIV", runtime.newFixnum(3));
    lambert.defineConstant("LambertIIExtended", runtime.newFixnum(4));
    lambert.defineConstant("Lambert93", runtime.newFixnum(5));

    RubyClass lambertPoint = lambert.defineClassUnder("LambertPoint",runtime.getObject(), new ObjectAllocator() {

      public IRubyObject allocate(Ruby runtime, RubyClass rubyClass){

        return new LambertPointRuby(runtime, rubyClass);
  }

    });

    lambertPoint.defineAnnotatedMethods(LambertPointRuby.class);
    lambertPoint.addReadAttribute(runtime.getCurrentContext(), "x");
    lambertPoint.addReadAttribute(runtime.getCurrentContext(), "y");
    lambertPoint.addReadAttribute(runtime.getCurrentContext(), "z");

       return true;
  }

  @JRubyClass(name = "Lambert::LambertPoint")
  public class LambertPointRuby extends RubyObject{

    public LambertPointRuby(final Ruby runtime, RubyClass rubyClass){
      super(runtime, rubyClass);

    }
    @JRubyMethod
    public IRubyObject initialize(ThreadContext context, IRubyObject x, IRubyObject y, IRubyObject z){

      setInstanceVariable("@x", x);
      setInstanceVariable("@y", y);
      setInstanceVariable("@z", z);

        return context.nil;
    }

    @JRubyMethod
    public void wgs84(ThreadContext context, IRubyObject zone){

      int zoneInt = (int) zone.convertToInteger().getLongValue();

      net.yageek.lambert.LambertZone jZone = net.yageek.lambert.LambertZone.Lambert93;
      switch(((int) zoneInt)){
        case 0: jZone = net.yageek.lambert.LambertZone.LambertI; break;
        case 1: jZone = net.yageek.lambert.LambertZone.LambertII; break;
        case 2: jZone = net.yageek.lambert.LambertZone.LambertIII; break;
        case 3: jZone = net.yageek.lambert.LambertZone.LambertIV; break;
        case 4: jZone = net.yageek.lambert.LambertZone.LambertIIExtended; break;
        case 5: jZone = net.yageek.lambert.LambertZone.Lambert93; break;
        default:
      }

      double x = getInstanceVariable("@x").convertToFloat().getDoubleValue();
      double y = getInstanceVariable("@y").convertToFloat().getDoubleValue();
      double z = getInstanceVariable("@z").convertToFloat().getDoubleValue();

      net.yageek.lambert.LambertPoint val = new net.yageek.lambert.LambertPoint(x, y, z);
      net.yageek.lambert.LambertPoint convertedVal = net.yageek.lambert.Lambert.convertToWGS84(val, jZone);
      convertedVal.toDegree();

      setInstanceVariable("@x",runtime.newFloat(convertedVal.getX()));
      setInstanceVariable("@y",runtime.newFloat(convertedVal.getY()));
      setInstanceVariable("@z",runtime.newFloat(convertedVal.getZ()));

    }

  }
  }
