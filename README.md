# Agentic AI Health Symptom Checker 🩺🤖
An AI-powered medical triage tool that uses Agentic AI, NLP, RAG, and IBM Granite LLM on IBM Cloud to provide personalized, reliable, and proactive health guidance.

This repository includes:
Hackathon-ready implementation for AI symptom checking.
Java client example to interact with IBM Granite LLM using IBM Cloud Machine Learning API.
Structured AI responses for safe and clear medical guidance.

🚀 Features
Natural Language Symptom Input – Describe symptoms in your own words, e.g., "I have a fever and sore throat".
Agentic AI Reasoning – Asks follow-up questions for accuracy.
Retrieval Augmented Generation (RAG) – References trusted medical sources.
Emergency Detection – Flags urgent symptoms.
Multi-language Support – Useful for rural & global audiences.
HIPAA-Compliant Privacy – Data handling designed for healthcare compliance.

🛠 Tech Stack
Cloud & AI Services
IBM Cloud Lite – Base platform for AI deployment.
IBM Granite Model – LLM for contextual reasoning.
IBM Watson NLU – Natural Language Processing.
IBM Watson Discovery – Retrieval Augmented Generation (RAG).
IBM Cloud Object Storage – Secure data storage.
IBM Cloud Databases – Patient record storage.

Application
Backend: Java (HttpURLConnection API) to connect to IBM Granite LLM.
Frontend: (Optional) React / Next.js for interactive UI.
Database: MongoDB / PostgreSQL for structured data.

📡 How It Works
User enters symptoms in plain language.
Java client requests IAM token from IBM Cloud using API key.
Java client sends request to IBM Granite LLM deployment endpoint.
Granite LLM processes symptoms and uses RAG to retrieve relevant medical info.
AI responds with probable causes, urgency level, and next-step recommendations.

📌 Example Java Request
String payload = "{\"messages\":[{\"role\":\"user\",\"content\":\"I have a headache and fever\"}]}";
The Granite LLM then responds with something like:

{
  "response": "It appears you may have a viral infection such as the flu. Monitor your temperature, stay hydrated, and rest. Seek medical attention if symptoms worsen or last more than 3 days."
}
🔒 Security & Privacy
All requests are encrypted over HTTPS.
No personal identifiers stored without consent.
Complies with HIPAA-like privacy principles.

💡 Future Scope
Wearable integration for real-time health tracking.
Telemedicine integration for direct doctor consultation.
Voice & image input for symptom analysis (e.g., rash detection).
Offline AI mode for rural/remote areas.
