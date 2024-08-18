import SubscriptionCard from "./SubscriptionCard";

const Subscription = () => {
  const freePlan = [
    "1 GB storage",
    "Custom domain",
    "Email support",
    "Analytics dashboard",
    "Automated backups",
  ];

  const paidPlan = [
    "10 GB storage",
    "Custom domain",
    "Email support",
    "Analytics dashboard",
    "Automated backups",
    "Collaboration tools",
    "Customizable templates",
    "Integrations with popular apps",
  ];

  const annualPlan = [
    "Unlimited storage",
    "Custom domain",
    "Email support",
    "Priority support",
    "Analytics dashboard",
    "Automated backups",
    "Collaboration tools",
    "Customizable templates",
    "Integrations with popular apps",
    "Dedicated account manager",
  ];

  return (
    <div className="p-10">
      <h1 className="text-5xl font-semibold py-5 pb-16 text-center">Pricing</h1>
      <div className="flex flex-col lg:flex-row justify-center items-center gap-9">
        <SubscriptionCard
          data={{
            planName: "Free",
            features: freePlan,
            planType: "FREE",
            price: 0,
            buttonName: true ? "Current Plan" : "Get Started",
          }}
        />
        <SubscriptionCard
        data={{
            planName: "Monthly Paid Plan",
            features: paidPlan,
            planType: "MONTHLY",
            price: 799,
            buttonName: true ? "Current Plan" : "Get Started",
          }} />
        <SubscriptionCard
        data={{
            planName: "Annual Paid Plan",
            features: annualPlan,
            planType: "ANNUALLY",
            price: 6711,
            buttonName: true ? "Current Plan" : "Get Started",
          }} />
      </div>
    </div>
  );
};

export default Subscription;
