# camunda-playground

Bpm for programmers? 
I have saved here my notes and thoughts to help fellow developers to reason if BPM could help them. 
My opinion: Absolutely maybe!

During journey of understanding what workflow automation means in microservices world my topic has shifted a bit.
Now I want to know how to keep state consistent within domain of multiple microservices.

Name of game: Saga - here's for all nerds: "a long story about Scandinavian history, written in the Old Norse language in the Middle Ages, mainly in Iceland". 
But wait. Saga in literature is more generally "a long, detailed story of connected events". https://dictionary.cambridge.org/dictionary/english/saga 

## Shorter path

If you don't like to read too much here's short recipe

- Read Chapter 5, "Transactions and Queries in Microservices" from Morgan Bruce, Paulo A. Pereira "Microservices in Action". Manning 2018 . https://www.manning.com/books/microservices-in-action

Then try Camunda (enterprise version recommended)

Open tutorial and follow instructions at: https://camunda.com/learn/videos/
- "Get Started" for installation
- "for Java Developers" to get first impression on integrating process and java code
- "Error Handling" for saga & compensation

There's also nice shorts for rules (DMN) and process communication (human tasks, rest api, etc).

## Motivation 

Motivation of using Process / Workflow Automation lies on search for 
- excellency in long run (Strategic decision, often done by Management) 
-- making algorithms of company easy to reason and fast to change 
- or easier programming model (Tactical decision, often done by IT Department or Software Development Team) 
-- to prevent hidden monolith, i.e. temporary and physically coupled systems which need
to be deployed and operated as whole.

!["Strategy1"](pics/camundacon-2018-the-role-of-workflows-in-microservices-camunda-49-1024.jpg)

If BPM is seen as Strategic and not (only) Tactical tool journey using BPM might look like this
- Take in use: small problems solved locally
- Operate: it works
- Improve: Enhance and extend existing processes
- Extend: Find new use cases, share experiences, connect processes together

Most sophisticated BPM tools are using standard based process notation (BPMN) plus rule (DMN) and case (CMMN) descriptions. Not all engines are alike, but standars are expanding pool of experts.

## Camunda

Camunda BPM is good example of BPMN based workflow automation toolset. There's other toolsets also, especially single vendor driven offerings, but Camunda seems to be more than just ok for java based integration projects.

Camunda BPM can be used as standalone installation or embedded within host applications. When used as embedded workflow engine Camunda BPM offers native Java API's.

Camunda BPM, Signavio and Flowable are all rooted to Activiti. https://www.enterpriseirregulars.com/110881/another-rift-open-source-bpm-market-flowablebpm-forks-alfresco-activiti/

Camunda BPM is offered as Open Source (Apache 2.0 license), commercial version adds monitoring and optimization features https://camunda.com/enterprise/. Camunda can be seen as "best of breed" open source stack.

Camunda as company has recently (12/2018) raised 25 million euros of venture capital https://blog.camunda.com/post/2018/12/camunda-raises-series-a/. They are growing, just as last year https://blog.camunda.com/post/2017/12/camunda-year-in-review/.

Camunda develops also new lightweight Workflow engine for microservices https://zeebe.io/

From my standpoint it look like Camunda as company and Camunda BPM have some strong points

- Rooted on experiences from consulting service, codebase already tested
- Current offer fulfills standards of OMG, namely BPMN, DMN & CMMN
- Event based microservices use cases are covered by Camunda BPM (mature) and soon performance optimized with Zeebe (bleeding edge)

## BPM features

Bpm toolset provides typically
- modeling tools
- tasklist for user facing actions (manual action: yes/no questions, data entry, etc.)
- toolkits for custom application (libraries for building clients, querying states of processes, errors, etc.)
- analytics and problem solving tools (can be called cockpit or dashboard or whatever, shows state of single process or overall
system)
- admin tools (reports, usage rights, etc.)
- engine for running processes (routing, transforming, calling endpoints, starting compensation, returning values, ..)
- rule engine (executing rules, simulating rule changes on used data, etc.)
- repository storing process state
- often highest value of bpm suites offer comes from production ready adapters for protocols (hl7, hipaa, .. etc) and systems (sap,
siebel, salesforce, ..)

Here's Camundas architecture as example

!["architecture1"](pics/architecture-overview.png)

https://docs.camunda.org/manual/latest/introduction/

## BPM enables continuous improvement

It is seldom that systems do not change (actually: if they stay same they are dead), and thus it's valuable to build them easily adaptable.

There's theory: making process easy to understand and change makes it possible to measure and improve.

!["pwc"](pics/case-study-camunda-bpm-in-pwc-project-11-638.jpg "bpm as continuous development model")

https://www.slideshare.net/camunda/case-study-camunda-bpm-in-pwc-project

This might seem business jargon, not only because it comes from PWC, but as collaboration is hard it's really valuable if IT and
business units can use same model as single truth.

## BPM single vendor suite or best of breed?

There is clear distinction of BPM use cases to
- centrally managed, monolithic, process and information hubs for enterprises
- application or solution level workflow and process integration for routing and transformation 

Monolithic single vendor suites were traditionally thought as standardisation and cost saving tools. So: here we aim for efficiency.

Application and solution level point solutions can be called as api gateways, bpm tools, eai tools, enterprise service bus, etc. and they are most propably provided as part of IT operations or software development, and could by used to modernise enterprise.

Beware to use single vendor suite targeted for complex enterprise intergration task to "simple" solution embedded application integration use cases. Opposite is definitely also true: if you need lot of out of box connectors for ERM, CRM, SCM, PLM or .. (add acronyms here) solutions then low level engine alone might not really solve challenge you have.

Example: Users of Oracle SOA suite are on "in single vendor we believe" category. Don't try it without good reason. 

Example 2: Camunda BPM users are on "clean fast forward engine is all we need" category. It tries to do on thing well. Just try it.

Now when I have warned of big bad boys I forgot them. Vendor lock in is bad, monolithic single vendor stacks have their limits on flexibility and value. And they aren't really for programmers.

## BPM use cases

Process orchestration use case can be most common motivation to invest in BPM engine. Using BPM and BPMN / DMN promotes collaboration and visibility, and opens up possibilities to develop processes in whole company
level (no silos here).

!["gs1"](pics/richard-tarling-managing-director-and-randall-graebner-senior-engineer-from-goldman-sachs-camunda-day-nyc-2018-7-1024.jpg "bpm as model driven development")

Still, there is not single use case for BPM, but several of them. https://blog.bernd-ruecker.com/5-workflow-automation-use-cases-you-might-not-have-thought-of-9bdeb0e71996

Microservices, which per definition have database per service, are good candidates for external workflow control, when data needs to be consistent.

Saga pattern, originally described 1987 for long living transactions in database systems, can be implemented using BPM. https://microservices.io/patterns/data/saga.html

Saga patterns usage is reasoned in Microservices In Action as follows

- In a distributed transaction, you manage uncertainty using
  locks on data; without transactions, you manage uncertainty through semantically appropriate
  workflows that confirm, cancel, or compensate for actions as they occur.

Compensation, described in detail in Enterprise Integration Patterns, can be used to manage overall state of distributed services

- You use compensating actions in sagas to undo previous operations and return
  your system to a more consistent state. The system isn’t guaranteed to be returned to
  the original state; the appropriate actions depend on business semantics. This design
  approach makes writing business logic more complex — because you need to consider
  a wide range of potential scenarios — but is a great tool for building reliable interactions
  between distributed services.

These technical use cases are making eventually consistent data processing using distributed transactions and microservices reality. https://blog.bernd-ruecker.com/saga-how-to-implement-complex-business-transactions-without-two-phase-commit-e00aa41a1b1b

In case Model Driven Development (MDD) connects in your mind to something ugly, like "generate it from model, then customize" horror scenarios, just relax. Model really drives logic here, as BPMN is directly executed (interpreted runtime). So, once more, we don't have to worry about generation gap https://martinfowler.com/dslCatalog/generationGap.html

### BPMN as programming model

Process is described using BPMN. http://mlwiki.org/index.php/BPMN. Process flow is executed using workflow / BPM engine.

It's possible to use BPM as
- Tool converging work of Analysts & Developers, or even to get whole company involved
- Tool for Developers

!["paradigm1"](pics/developerfriendly-bpm-10-1024.jpg)

It's up to who drives development - or if it is done together
 
Analyst drives
- model, deploy, run => then handover to developers (if needed)

Developer drives
- model => then handover to developers, who develop, deploy, run, and get back to analyst with questions

Together
- modelling, implementing and testing processes is done together iteratively

!["talanx1"](pics/camundacon-2018-our-journey-to-the-digital-world-of-insurance-talanx-26-1024.jpg "bpmn as programming model")

Service Endpoint can be http (soa, rest) or java or other jvm language (in case bpm engine supports this)

Some JVM languages to try

Lisp syntax
- Ever thought how Camunda BPM works with Clojure? Someone has https://github.com/tobiasbehr/camunda-clojure

Kotlin
- Nothing beats community: Here's Kotlin example https://github.com/holunda-io/camunda-springboot-kotlin

### DMN as programming model

Rules are described using DMN. http://www.bpm-guide.de/2015/07/20/dmn-decision-model-and-notation-introduction-by-example/. DMN is very analyst friendly way of using single or multiple related decision tables.

-  Decisions can be modeled and executed using the same notation. This allows to use business analysis results as „code“ making changes to the rules behind the decision really easy. Given appropriate tooling it might even be realistic that business people can directly edit concrete rules within a given structure (more on this later). As rules (leading to decisions) change much more often that processes or entities, being agile in changing rules is absolutely necessary for being future ready!

!["talanx2"](pics/camundacon-2018-our-journey-to-the-digital-world-of-insurance-talanx-27-1024.jpg "dmn as programming model")

DMN (Decision Modeling Notation) can be used to model decision done based on data present runtime in process instance. Results of DMN execution are saved to process instance and process can branch based on this newly reasoned state.

DMN is expressed using XML syntax. There's also possibility to generate native java from DMN rules, which makes execution of rules fast and independent of Camunda.

!["gs2"](pics/richard-tarling-managing-director-and-randall-graebner-senior-engineer-from-goldman-sachs-camunda-day-nyc-2018-14-1024.jpg "perfornabt DMN execution")

Managing rules is evidently good idea if there's thousands of them. At that point it might even be mandatory formalize rules and save
them to repository to fight against accidental complexity.

## Microservices: BPM engine as controlling component

- In short, the microservice architectural style is an approach to developing a single application as a suite of small services, each running in its own process and communicating with lightweight mechanisms, often an HTTP resource API. These services are built around business capabilities and independently deployable by fully automated deployment machinery. There is a bare minimum of centralized management of these services, which may be written in different programming languages and use different data storage technologies.  
  -- James Lewis and Martin Fowler

Balance between independent modules and central control of processes might be hard to find.

To me central controlling component enables services to be small and independent as there's controller function which controls complex interactions of them.

BPM engine does controlling by
- orchestrating flow of service calls
- correlating messages / events to process
- reacting on errors (service call fails)
- reacting on timeouts (service call result missing)
- enabling compensation (transactionality, replaces 2pc)

!["br1"](pics/camundacon-2018-the-role-of-workflows-in-microservices-camunda-21-1024.jpg "BPM as balancing act")

If plan is to use microservices, i.e single purpose lightweight services, coupling of them together needs to happen somewhere else. Especially handling failures and rollbacks is complex and needs to be planned ahead.
- Microservices shouldn't know each other
- Each call to microservices should be idempotent (always leave service to same state with same parameters)
- Microservice should support compensation (return to previous state)

It's very hard to see what happens at global level, as microservices are very small and do limited amount of work and thus have limited
complexity.

Simply put: complexity needs to go somewhere else. To global flow of services, which, in it's best is controlled.

!["br2"](pics/camundacon-2018-the-role-of-workflows-in-microservices-camunda-48-1024.jpg "BPM and microservices")

Saga pattern pops up here as orchestrated implementation described in Microservices in Action.

- In an orchestrated saga, a service takes on the role of orchestrator (or coordinator): a process that
  executes and tracks the outcome of a saga across multiple services. An orchestrator
  might be an independent service or a capability of an existing service.
- The sole responsibility of the orchestrator is to manage the execution of the saga. It
  may interact with participants in the saga via asynchronous events or request/response
  messages. Most importantly, it should track the state of execution for each stage in the
  process; this is sometimes called the saga log. 
    
It should be by now ecvident that "orchestrator" is here BPM engine, ans SAGA is single process or subprocess.

Saga pattern is like most of implementation strategies. No silver bullets here.

- Centralizing the saga’s sequencing logic in a single service makes it significantly
  easier to reason about the outcome and progress of that saga, as well as change
  the sequencing in one place. In turn, this can simplify individual services, reducing
  the complexity of states they need to manage, because that logic moves to the
  coordinator.
- This approach does run the risk of moving too much logic to the coordinator. At
  worst, this makes the other services anemic wrappers for data storage, rather than
  autonomous and independently responsible business capabilities.

No Locks. No ACID. No isolation. From data point of view Sagas & Long running processes - whether choreographed or orchestrated - have more persistent states as intermediate states are persisted and can be modified futher. 

Microservices in Action presents your new world like this
- Unlike ACID transactions, sagas aren’t isolated. The result of each local transaction is
  immediately visible to other transactions affecting that entity. This visibility means that
  a given entity might get simultaneously involved in multiple, concurrent sagas. As such,
  you need to design your business logic to expect and handle intermediate states. The
  complexity of the interleaving required primarily depends on the nature of the underlying
  business logic.

## BPM deployment options

### BPM Engine as black box

In this model process orchestration service is running as independent installation without possibly any customization.

!["br3"](pics/camunda-blackbox.png "Camunda as black box")

There's no restrictions on communication models, except that all process calls are distributed
- Messaging using events / topics / message queues - everything completely async
- Messaging using rest, some calls return results in request / response, some use callbacks
- Mixed: events, rest - whatever

If integration service, and all services it uses, are inside DDD Bounded context, whole can be seen as just one service, doesn't matter that it doesn't have any JAVA inside or BPM is not embedded within service.

This is centralized model of running processes, but it works and is easy to implement.

### BPM Engine as white box

When BPM engine is seen as white box you have every feature of black box in use, but also
- can embed BPM engine to your app
- can start processes as you wish (local call from your service logic)
- endpoints can be java classes (inprocess calls from bpm engine)

https://blog.bernd-ruecker.com/architecture-options-to-run-a-workflow-engine-6c2419902d91

When BPM engine is local to logic, it's just an implementation detail, but still has admin tools present and process state is in database
(might be in memory db, but doesn't need to be)

!["talanx3"](pics/camundacon-2018-our-journey-to-the-digital-world-of-insurance-talanx-51-1024.jpg "BPM inside microservice")

When taking bird view it looks like this. There's several microservices, organized to layers (process microservices, atomic microservices), each microservice with complex interaction requirements has their own bpm engine, and process flow of microservices which use bpm can be
analyzed using bpm engines administration and optimization tools . 

## BPM architecture options

### BPM within Event based architecture(s)

In event based architecture all systems are decoupled and exchanges messages using shared bus. It's not evident to me if there's single or multiple valid event based architectures. https://martinfowler.com/articles/201701-event-driven.html

-  it's very easy to make nicely decoupled systems with event notification, without realizing that you're losing sight of that larger-scale flow, and thus set yourself up for trouble in future years. 
   -- Martin Fowler

Every interaction in using async Events, and apps just tell something has happened. None controls here anything, everyone listens and reacts if event
seems important.

!["br4"](pics/camundacon-2018-the-role-of-workflows-in-microservices-camunda-42-1024.jpg "Bpm and Events")

Some call this model of process execution "choreography" (functionality emerges from interactions) versus "orchestration" (single party
is leading interactions and dictating possible functionality)

## BPM within Rest based architecture

In REST (or gRPC or .. since mostly this is RPC, and can as well use efficient binary formats) architecture services need to know endpoints which take part to process in addition to message payload (json), but bus is away,
which can make deployment easier at least in small use cases

!["br5"](pics/camundacon-2018-the-role-of-workflows-in-microservices-camunda-43-1024.jpg "Bpm and Rest")

Note that here order is controlling interactions so we have here "orchestration" as process model. There's also long running state in Payment, as it needs to wait until user has paid or canceled payment request.

How to implement java microservices? Easy. Just choose your tools. 

- Spring Boot. http://spring.io/projects/spring-boot
- Eclipse MicroProfile. https://microprofile.io/
- Lagom. https://www.lightbend.com/lagom-framework
- and many others .. 

## Distributed computing 

Taking as first principle of design that distributed system functions like non distributed is often connected to Fallacies of distributed
computing https://en.wikipedia.org/wiki/Fallacies_of_distributed_computing

Principles of chaos engineering states http://principlesofchaos.org/
- Even when all of the individual services in a distributed system are functioning properly, the interactions between those services
  can cause unpredictable outcomes. Unpredictable outcomes, compounded by rare but disruptive real-world events that affect
  production environments, make these distributed systems inherently chaotic.
  
Netfix fights against this by trying to fail fast and always only partially, and tests system of systems using chaos monkey, which tries to
beak everything https://github.com/Netflix/SimianArmy/wiki/Chaos-Monkey

Nextfilx has simple motivation for breaking things ..
- Failures happen, and they inevitably happen when least desired. If your application can't tolerate a system failure would you
  rather find out by being paged at 3am or after you are in the office having already had your morning coffee?
  
I'd prefer seeing all communication and systems as inreliable, and failure as normal, but it's evidently simpler to trust on every
component and not take existing complexity as "my problem" or "normal complexity"

Some vendors see that whole current service model - or even software architectures we use as de facto way - are obsolete

!["lb1"](pics/the-future-of-services-building-asynchronous-resilient-and-elastic-systems-7-1024.jpg)

But it's clear that this change adds complexity somewhere while it at the same time tries to reduce it somewhere else

!["lb2"](pics/the-future-of-services-building-asynchronous-resilient-and-elastic-systems-16-1024.jpg)

If efficiency is important everything possible would need to be async! Rest is heavy ...

!["lb3"](pics/the-future-of-services-building-asynchronous-resilient-and-elastic-systems-12-1024.jpg)

It's inevitable that you should pay some attention to these ideas. https://www.slideshare.net/Lightbend/the-future-of-services-building-asynchronous-resilient-and-elastic-systems

## Errors in Rest communication

Rest communication is de-facto easiest way to integrate services (connections, http handling, json handling, security tokens, etc..)

But beware: Long response times are creating deadlocks and blocking resources, which might slow down overall system even if problem is only
affecting single component. To enjoy yourself with disaster stories take one post-mortem and congratulate yourself if you were not at that project. http://oredev.org/2016/sessions/avoiding-microservice-megadisasters

If service is called using asynchronous rest / http call result can be
- Success: Service replied and client knows state of server (might be slow, but response comes eventually)
- Failure 1: Service never got request (no state change at service)
- Failure 2: Service failed with no reply (state of service should be unchanged)
- Failure 3: Client never got reply (state of service changed)

In Failure cases 1-3 client can and need to send retry request, since it doesn't have any idea what happened at server end.

In server situation is different when client sends retry:
- In Failure 1 server gets request first time
- in Failure 2 server might have partial processing done (if transaction is not rolled back properly state might be dirty, also: service
might be down or unstable due to problems which prevented processing before)
- In Failure 3 everything is done - so, here idempotent operation is ok, otherwise everything is "doppel gemoppelt"

!["rest1"](pics/paul-lungu-microservices-integration-challenges-and-solutions-camunda-day-new-york-city-6-1024.jpg)

Notice: Idempotent service needs some extra work from implementation. It should also be clear where it is needed and where not.

- Es ist unbedingt erforderlich, dass sich Services idempotent verhalten, wenn dies angebracht ist. Und genau das muss
  individuell für jeden Service entschieden werden. Soll ein Service idempotent sein, so muss er diese Idempotenz selbst
  implementieren und gegebenenfalls vor der Verarbeitung selbst prüfen, ob die diese bereits durchgeführt wurde.
  
  https://blog.holisticon.de/2012/11/immer-das-gleiche-mit-der-idempotenz/

## Communications design

Different communication models results result different software structure
- R/R being tighter coupled, but very clearly structured
- Event-driven components having low coupling, but harder to debug, operate and reason

!["cc1"](pics/resilient-functional-service-design-56-1024.jpg)

There needs to be basic understanding of new model if team want to use event based communication, since it needs lot of learning

!["cc2"](pics/resilient-functional-service-design-57-1024.jpg)

It's important to note that architecturally using BPM can be somewhere in between these lines: communication can still be R/R, but
there's supervisor and escalation methods like retry and compensation in place and failure of single component can result alternative
path of execution to be chosen (for example initial response notes exceptional situation and callback is bringing result later when
system is stable again)

## Extension to Rest communication

With BPM engine it's possible to do some "fancy" stuff - for example decide at service if processing is sync or async - process as client
adapts to decisions of service.

Service is here using BPM to implementation multi part processing
- Return result if it's ready FAST (sync)
- Return ack if result takes time (async)
- Return result to waiting process when it's ready

Client can be other process in other process engine.

!["cc2"](pics/paul-lungu-microservices-integration-challenges-and-solutions-camunda-day-new-york-city-8-638.jpg)

I haven't yet really understood if this flexibility has value.. 

## Mapping process data to services

There's several strategies to map process data to systems which implement process.

Easiest is to have canonical data model for enterprise - this means: no mapping, or mapping in systems implementation. https://dzone.com/articles/still-struggling-between-platforms-and-microservic

One way of this is to use open api, possibly so that BPMN models and endpoints are defined by same people. https://www.openapis.org/

And well, yes. If canonical model goes wrong or it doesn't just evolve but needs big refactoring you might be in such a trouble that it's better to change workplace before anyone understands magnitude of problem resulting from your teams nice canonical model. https://www.innoq.com/en/blog/thoughts-on-a-canonical-data-model/

If there's no canonical data model mapping is needed. No problem. Now try to do anything locally. https://medium.com/@nathankpeck/microservice-principles-smart-endpoints-and-dumb-pipes-5691d410700f

!["mapping1"](pics/20180821-camunda-meetup-berlinrobert-breske-12-1024.jpg)

# BPM loves Robots :D

Robotic Process Automation is way to open legacy systems using proxies. 

Ask yourself why this is done? Simple: Legacy is expensive, proxies are cheap.

!["rpa1"](pics/jakob-freund-camunda-for-it-executives-camunda-days-21-638.jpg)

Legacy system is just an endpoint to BPM - it may be replaced later with real integration if and when possible.

!["rpa2"](pics/jakob-freund-camunda-for-it-executives-camunda-days-24-1024.jpg)

RPA is strategy to buy bit of time, not really a suitable way to modernize existing systems.

## BPM as part of "Layered Microservices Architecture"

It looks like oxymoron, and probably is: chain of coupled services are depicted as layers.

But still, if we follow thought of layering it's clear that BPM enabled services would be part of internal layer.

!["layered1"](pics/layered-microservices-edge.png)

Or we could see them as Composite/Integration services

!["layered2"](pics/layered-microservices-composite.png)

Still to note: layers should be organized by business purpose, which means that composite/integration => core/atomic services seem to
be ok idea as long as composite/integration is providing additional value, but edge => internal => external might result wrong granularity
and thus create problematic division of work.

- It's too easy for technology teams to be assigned by layer, so delivering any valuable business change requires slow and
  expensive coordination between multiple teams. We caution against the effects of this layering and recommend arranging
  services and teams primarily according to business capability.
  
  https://www.thoughtworks.com/radar/techniques/layered-microservices-architecture

## Reality check

https://www.slideshare.net/ufried?utm_campaign=profiletracking&utm_medium=sssite&utm_source=ssslideview

## References

https://www.slideshare.net/Lightbend/the-future-of-services-building-asynchronous-resilient-and-elastic-systems

https://www.slideshare.net/ufried/resilient-functional-service-design

https://www.slideshare.net/camunda/20180821-camunda-meetup-berlinrobert-breske

https://blog.bernd-ruecker.com/use-camunda-without-touching-java-and-get-an-easy-to-use-rest-based-orchestration-and-workflow-7bdf25ac198e 

https://www.slideshare.net/camunda/camundacon-2018-the-reemergence-of-workflow-automation-keynote-camunda

https://www.slideshare.net/skemsley/developerfriendly-bpm
