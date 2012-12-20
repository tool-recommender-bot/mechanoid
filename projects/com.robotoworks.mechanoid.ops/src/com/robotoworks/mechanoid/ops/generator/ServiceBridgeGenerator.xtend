package com.robotoworks.mechanoid.ops.generator


import static extension com.robotoworks.mechanoid.common.util.Strings.*
import static extension com.robotoworks.mechanoid.ops.generator.Extensions.*
import com.robotoworks.mechanoid.ops.opServiceModel.Model
import com.robotoworks.mechanoid.ops.opServiceModel.Operation

class ServiceBridgeGenerator {
		def CharSequence generate(Model model) '''
			�var svc = model.service�
			/*
			 * Generated by Robotoworks Mechanoid
			 */
			package �model.packageName�;
			
			import com.robotoworks.mechanoid.ops.OperationServiceBridge;
			import �model.packageName�.�svc.name.pascalize�Service;
			import android.content.Intent;
			import com.robotoworks.mechanoid.Mechanoid;			
			
			public abstract class Abstract�svc.name.pascalize�ServiceBridge extends OperationServiceBridge {
				private static final Class<?> SERVICE_CLASS = �svc.name.pascalize�Service.class;
			
				protected Class<?> getServiceClass() {
					return SERVICE_CLASS;
				}
			
				
				�FOR op : svc.ops�
				public int execute�op.name.pascalize�Operation(�FOR arg : op.args SEPARATOR ', '��arg.type.toTypeLiteral� �arg.name.camelize��ENDFOR�) {
					Intent intent = Abstract�op.name.pascalize�Operation.create�op.name.pascalize�Intent(�FOR arg : op.args SEPARATOR ', '��arg.name.camelize��ENDFOR�);
					
					�IF op.unique != null && op.unique.args.size > 0�
					android.os.Bundle matcher = new android.os.Bundle();
					�FOR uarg : op.unique.args�
					matcher.�uarg.type.toBundlePutMethodName�(Abstract�op.name.pascalize�Operation.EXTRA_�uarg.name.underscore.toUpperCase�, �uarg.name.camelize�);
					�ENDFOR�
			
					Intent existingRequest = findPendingRequestByActionWithExtras(Abstract�op.name.pascalize�Operation.ACTION_�op.name.underscore.toUpperCase�, matcher);
					�ELSE�
					Intent existingRequest = findPendingRequestByActionWithExtras(Abstract�op.name.pascalize�Operation.ACTION_�op.name.underscore.toUpperCase�, intent.getExtras());
					�ENDIF�
					if(existingRequest != null) {
						return extractRequestId(existingRequest);
					}
					
					int requestId = createServiceRequest(intent);
					
					Mechanoid.startService(intent);
					
					return requestId;
				}
				�ENDFOR�
			}
			'''
			
		def CharSequence generateStub(Model model) '''
			�var svc = model.service�
			/*
			 * Generated by Robotoworks Mechanoid
			 */
			package �model.packageName�;
			
			public class �svc.name.pascalize�ServiceBridge extends Abstract�svc.name.pascalize�ServiceBridge {

				private static �svc.name.pascalize�ServiceBridge instance;
				
				public static �svc.name.pascalize�ServiceBridge getInstance() {
					if(instance == null) {
						instance = new �svc.name.pascalize�ServiceBridge();
					} 
					return instance;
				}
				
				private �svc.name.pascalize�ServiceBridge(){}
			
			}
			
		'''
}