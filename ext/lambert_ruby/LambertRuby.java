package net.yageek.lambertruby;


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
import org.jruby.runtime.ObjectAllocator;
import org.jruby.runtime.ThreadContext;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.runtime.load.BasicLibraryService;


import net.yageek.lambert.*;

public class LambertRuby implements BasicLibraryService {

  private Ruby runtime;

  public boolean basicLoad(Ruby runtime) throws IOException {
    this.runtime = runtime;

    RubyModule lambert = runtime.defineModule("Lambert");


    RubyClass lambertPoint = lambert.defineClassUnder("LambertPoint",runtime.getObject(), new ObjectAllocator() {

      public IRubyObject allocate(Ruby runtime, RubyClass rubyClass){

        return new LambertPoint(runtime, rubyClass);

  }

    });

      lambertPoint.defineAnnotatedMethods(LambertPoint.class);

       return true;
  }


  public class LambertPoint extends RubyObject{

    private net.yageek.lambert.LambertPoint jPoint;

    public LambertPoint(final Ruby runtime, RubyClass rubyClass){
      super(runtime, rubyClass);

      setInstanceVariable("@x",runtime.newFloat(0.0));
      setInstanceVariable("@y",runtime.newFloat(0.0));
      setInstanceVariable("@z",runtime.newFloat(0.0));

    }


    @JRubyMethod
    public void initialize(Thread context, IRubyObject x, IRubyObject y,IRubyObject z){
        this.jPoint = new net.yageek.lambert.LambertPoint(x.convertToFloat().getDoubleValue() ,y.convertToFloat().getDoubleValue(), z.convertToFloat().getDoubleValue());
    }


    @JRubyMethod
    public void wgs84(ThreadContext context, IRubyObject zone){

      long zoneInt = ((RubyFixnum) zone).getLongValue();

      LambertZone jZone = net.yageek.lambert.LambertZone.Lambert93;;
      switch(((int) zoneInt)){
        case 0: jZone = net.yageek.lambert.LambertZone.LambertI; break;
        case 1: jZone = net.yageek.lambert.LambertZone.LambertII; break;
        case 2: jZone = net.yageek.lambert.LambertZone.LambertIII; break;
        case 3: jZone = net.yageek.lambert.LambertZone.LambertIV; break;
        case 4: jZone = net.yageek.lambert.LambertZone.LambertIIExtended; break;
        case 5: jZone = net.yageek.lambert.LambertZone.Lambert93; break;
        default:
      }

      this.jPoint = net.yageek.lambert.Lambert.convertToWGS84(this.jPoint, jZone);
      this.jPoint.toDegree();

      setInstanceVariable("@x",runtime.newFloat(jPoint.getX()));
      setInstanceVariable("@y",runtime.newFloat(jPoint.getY()));
      setInstanceVariable("@z",runtime.newFloat(jPoint.getZ()));

    }



  }
  }
