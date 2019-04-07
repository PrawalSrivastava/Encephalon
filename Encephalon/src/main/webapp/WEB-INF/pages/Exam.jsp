<div class="row " id="ExamDiv" ng-controller="ExamController" >
    <div class="col-md-12">
        <div class="card " style="height: 100%">
            <div class="card-header">
                <!--Mention Question Type-->
                <span>{{type}}</span>
                <ul class="breadcrumb">
                    <li><a href="#">Home</a> <span class="divider">/</span></li>
                    <li><a href="#">Library</a> <span class="divider">/</span></li>
                    <li class="active">Data</li>
                </ul>
                <!--THen mention Question Number-->
                <span class="float-right clearfix">Question No: {{questionNumber + 1}}</span>
                
            </div>
            <div class="card-body">
                <div id="wordMeaningLayout1" ng-show="displayLayout.wordMeaningLayout1">
                    <!--for word--> 
                    <b id="wordSpan" >Word :</b>
                    <span id="word" ng-bind="wmResult.word"></span>
                    <!--for meaning-->
                    <div id="meaningBlock" class="pl-3">
                        <!--for noun-->
                        <div ng-repeat="lexicalEntry in wmResult.lexicalEntries">
                            <b id="lexicalCategory" class="font-italic" >{{lexicalEntry.lexicalCategory}}</b>
                            <!--                                                    this is going to repeat
                                                                                for difinition-->
                            <div id="difinitionBlock" ng-repeat="entry in lexicalEntry.entries">
                                <div id="difinition" ng-repeat="sense in entry.senses">
                                    <div ng-repeat="definition in sense.definitions" class="pl-3">
                                        <!--for dfinition word-->
                                        <span id="difinitionWord" class="text-muted" >Definition: </span>
                                        <!--for definition--> 
                                        <span id="definitionText" ng-bind="definition"></span>
                                        <br/>
                                    </div>
                                    <!--for dfinition word-->
                                    <span id="difinitionWord"  ng-show="definition.usage">Usage: </span>
                                    <!--for definition--> 
                                    <span id="definitionText" ng-bind="x.example"></span>
                                    <!--repeat for synonyms-->
                                    <span id="synonymsBlock" ng-repeat="y in x.synonyms">{{y}}</span>
                                </div>

                            </div>
                        </div>
                        <!--for verb This is going to repeat or may be not-->
                        <div>

                        </div>
                    </div>
                    <br/>
                    <br/>
                    <br/>
                    <div id="layout1RadioOptionsDiv" ng-click="questionAnswered($event)">
                        <input name="layout1RadioOptions" type="radio" > I Remember</input>
                        <br/>
                        <input name="layout1RadioOptions" type="radio" > I Don't Remember</input>
                    </div>
                </div>
                <div id="wordMeaningLayout2" ng-show="displayLayout.wordMeaningLayout2">

                </div>
                <div id="pointToRememberLayout" ng-show="displayLayout.pointToRememberLayout">
                    <!--for word--> 
                    <b id="pointSpan" >Point :</b>
                    <pre id="point" ng-bind="ptrResult.point"></pre>
                    <br/>
                    <b id="answerSpan" >Answer :</b>
                    <pre id="answer" class="pl-3" ng-bind="ptrResult.answer"></pre>
                    <br/>
                    <br/>
                    <br/>
                    <div id="layout1RadioOptionsDiv" ng-click="questionAnswered($event)" >
                        <input name="layout1RadioOptions" type="radio" > I Remember</input>
                        <br/>
                        <input name="layout1RadioOptions" type="radio" > I Don't Remember</input>
                    </div>
                </div>
            </div>
            <div class="card-footer">
                <!--In the center Mention Previous-->
                <button class="btn btn-default" ng-click="previousQuestion($event)">Previous</button>
                <!--THen Next-->
                <button class="btn btn-primary"ng-click="nextQuestion($event)">Next</button>
                <!--Then Submit-->
                <button class="btn btn-success float-right" ng-click="handleSubmit($event)">Submit</button>
            </div>
        </div>
    </div>
</div>