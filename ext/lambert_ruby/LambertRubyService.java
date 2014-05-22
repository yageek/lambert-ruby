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


import net.yageek.lambert.*;

public class LambertRubyService implements BasicLibraryService {

  private Ruby runtime;

  public boolean basicLoad(Ruby runtime) throws IOException {
    this.runtime = runtime;

    RubyModule lambert = runtime.defineModule("Lambert");


    RubyClass lambertPoint = lambert.defineClassUnder("LambertPoint",runtime.getObject(), new ObjectAllocator() {

      public IRubyObject allocate(Ruby runtime, RubyClass rubyClass){

        return new LambertPointRuby(runtime, rubyClass);
  }

    });

      lambertPoint.defineAnnotatedMethods(LambertPointRuby.class);

       return true;
  }

  @JRubyClass(name = "Lambert::LambertPoint")
  public class LambertPointRuby extends RubyObject{

    private LambertPoint jPoint;

    public LambertPointRuby(final Ruby runtime, RubyClass rubyClass){
      super(runtime, rubyClass);

      setInstanceVariable("@x",runtime.newFloat(0.0));
      setInstanceVariable("@y",runtime.newFloat(0.0));
      setInstanceVariable("@z",runtime.newFloat(0.0));

    }


    @JRubyMethod
    public void initialize(Thread context, IRubyObject x, IRubyObject y,IRubyObject z){
        this.jPoint = new LambertPoint(x.convertToFloat().getDoubleValue() ,y.convertToFloat().getDoubleValue(), z.convertToFloat().getDoubleValue());
    }


    @JRubyMethod
    public void wgs84(ThreadContext context, IRubyObject zone){

      long zoneInt = ((RubyFixnum) zone).getLongValue();

      LambertZone jZone = net.yageek.lambert.LambertZone.Lambert93;;
      switch(((int) zoneInt)){
        case 0: jZone = LambertZone.LambertI; break;
        case 1: jZone = LambertZone.LambertII; break;
        case 2: jZone = LambertZone.LambertIII; break;
        case 3: jZone = LambertZone.LambertIV; break;
        case 4: jZone = LambertZone.LambertIIExtended; break;
        case 5: jZone = LambertZone.Lambert93; break;
        default:
      }

      this.jPoint = Lambert.convertToWGS84(this.jPoint, jZone);
      this.jPoint.toDegree();

      setInstanceVariable("@x",runtime.newFloat(jPoint.getX()));
      setInstanceVariable("@y",runtime.newFloat(jPoint.getY()));
      setInstanceVariable("@z",runtime.newFloat(jPoint.getZ()));

    }

  }
  }
