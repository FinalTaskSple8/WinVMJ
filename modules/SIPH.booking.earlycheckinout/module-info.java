module SIPH.booking.earlycheckinout {
	requires SIPH.room.core;
	requires SIPH.booking.core;
    exports SIPH.booking.earlycheckinout;

	requires vmj.routing.route;
	requires vmj.hibernate.integrator;
	requires vmj.auth;
	requires java.logging;
	// https://stackoverflow.com/questions/46488346/error32-13-error-cannot-access-referenceable-class-file-for-javax-naming-re/50568217
	requires java.naming;
	requires java.net.http;

	opens SIPH.booking.earlycheckinout to org.hibernate.orm.core, gson, vmj.hibernate.integrator;
}
