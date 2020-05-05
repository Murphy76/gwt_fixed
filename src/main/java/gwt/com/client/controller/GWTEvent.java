package gwt.com.client.controller;

import com.google.gwt.event.shared.GwtEvent;

public class GWTEvent extends GwtEvent<GWTEventHandler> {
	 
	public static Type<GWTEventHandler> TYPE = new Type<GWTEventHandler>();
		private int [] cutPoint;
	 
	    public GWTEvent(int [] numbers) {
		
		cutPoint = numbers;
	}

		@Override
	    public com.google.gwt.event.shared.GwtEvent.Type<GWTEventHandler> getAssociatedType() {
	        return TYPE;
	    }
	 
	    @Override
	    protected void dispatch(GWTEventHandler handler) {
	        handler.onEvent(this);
	    }

		public int[] getCutPoint() {
			return cutPoint;
		}

		
	    
}
