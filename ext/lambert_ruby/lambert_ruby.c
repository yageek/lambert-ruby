#include <ruby.h>
#include "lambert.h"

static VALUE rb_mLambert;
static VALUE rb_cPoint;

static VALUE p_init(int argc, VALUE* argv, VALUE self)
{

  VALUE x, y, z;

  rb_scan_args(argc,argv, "21",&x, &y, &z);

  if(NIL_P(z))
  {
  	z = rb_float_new(0.0);
  }

  Check_Type(x,T_FLOAT);
  Check_Type(y,T_FLOAT);
  Check_Type(z,T_FLOAT);

  rb_iv_set(self, "@x", x);
  rb_iv_set(self, "@y", y);
  rb_iv_set(self, "@z", z);

  return self;
}

static VALUE p_convert(VALUE self,VALUE zone){

	double x, y, z;
	YGLambertZone cZone = NUM2INT(zone);

	x = NUM2DBL(rb_iv_get(self,"@x"));
	y = NUM2DBL(rb_iv_get(self,"@y"));
	z = NUM2DBL(rb_iv_get(self,"@z"));

	YGPoint org = YGMeterPoint(x,y,z);
	org = YGPointConvertWGS84(org,cZone);
	org = YGPointToDegree(org);

   rb_iv_set(self, "@x", rb_float_new(org.x));
   rb_iv_set(self, "@y", rb_float_new(org.y));
   rb_iv_set(self, "@z", rb_float_new(org.z));


	return Qnil;
}

void Init_lambert_ruby(void) {

	rb_mLambert = rb_define_module("Lambert");
	rb_cPoint = rb_define_class_under(rb_mLambert,"LambertPoint",rb_cObject);
	rb_define_attr(rb_cPoint,"x",1,1);
	rb_define_attr(rb_cPoint,"y",1,1);
	rb_define_attr(rb_cPoint,"z",1,1);

	rb_define_method(rb_cPoint,"initialize",p_init,-1);
	rb_define_method(rb_cPoint,"wgs84",p_convert,1);

	rb_define_const(rb_mLambert,"LambertI",INT2NUM(LAMBERT_I));
	rb_define_const(rb_mLambert,"LambertII",INT2NUM(LAMBERT_II));
	rb_define_const(rb_mLambert,"LambertIII",INT2NUM(LAMBERT_III));
	rb_define_const(rb_mLambert,"LambertIV",INT2NUM(LAMBERT_IV));
	rb_define_const(rb_mLambert,"LambertIIExtended",INT2NUM(LAMBERT_II_E));
	rb_define_const(rb_mLambert,"Lambert93",INT2NUM(LAMBERT_93));
}
