package main.java.service;

public class Service {

	private int log_id;
	
	private int memberId;
	
	private String serviceName;
	
	private String registerTime;
	
	private double price;
	
	private double sale;
	
	private double mustPay;
	
	private double paid;
	
	private String paymentMethod;
	
	public Service(int log_id, String serviceName, String registerTime, double price, double sale,
			double mustPay, double paid, String paymentMethod) {
		super();
		this.log_id = log_id;
		this.serviceName = serviceName;
		this.registerTime = registerTime;
		this.price = price;
		this.sale = sale;
		this.mustPay = mustPay;
		this.paid = paid;
		this.paymentMethod = paymentMethod;
	}

	public int getLog_id() {
		return log_id;
	}

	public void setLog_id(int log_id) {
		this.log_id = log_id;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getSale() {
		return sale;
	}

	public void setSale(double sale) {
		this.sale = sale;
	}

	public double getMustPay() {
		return mustPay;
	}

	public void setMustPay(double mustPay) {
		this.mustPay = mustPay;
	}

	public double getPaid() {
		return paid;
	}

	public void setPaid(double paid) {
		this.paid = paid;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	
}
